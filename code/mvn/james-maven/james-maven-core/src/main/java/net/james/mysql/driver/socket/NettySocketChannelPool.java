package net.james.mysql.driver.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/**
 * 实现channel的管理（监听连接、读数据、回收）
 * author: yang
 * datetime: 2021/11/1 16:30
 */

public class NettySocketChannelPool {

    private static Logger logger = Logger.getLogger(NettySocketChannelPool.class.getName());

    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Bootstrap boot = new Bootstrap();
    private static Map<Channel, SocketChannel> chManager = new ConcurrentHashMap<>();



    public static SocketChannel open(SocketAddress address) throws Exception {
        SocketChannel socket = null;
        boot.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new BusinessHandler());
                        // 命令过滤和handler添加管理
                    }
                });
        ChannelFuture future = boot.connect(address).sync();

        if (future.isSuccess()) {
            future.channel().pipeline().get(BusinessHandler.class).latch.await();
            socket = chManager.get(future.channel());
        }

        if (null == socket) {
            throw new IOException("can't create socket!");
        }

        return socket;
    }

    public static class BusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {

        private NettySocketChannel   socket = null;
        private final CountDownLatch latch  = new CountDownLatch(1);

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            socket.setChannel(null);
            chManager.remove(ctx.channel());// 移除
            super.channelInactive(ctx);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            socket = new NettySocketChannel();
            socket.setChannel(ctx.channel());
            chManager.put(ctx.channel(), socket);
            latch.countDown();
            super.channelActive(ctx);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            if (socket != null) {
                socket.writeCache(msg);
            } else {
                // TODO: need graceful error handler.
                logger.info("no socket available.");
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            // need output error for troubeshooting.
            logger.info("business error."+cause);
            ctx.close();
        }
    }
}
