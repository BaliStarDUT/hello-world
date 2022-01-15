package net.james.mysql.driver.socket;


import java.net.SocketAddress;
import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2021/11/1 16:28
 */

public class SocketChannelPool {

    private static Logger logger = Logger.getLogger(SocketChannelPool.class.getName());

    public static SocketChannel open(SocketAddress address) throws Exception {
        String type = "bio"; // bio or netty
//        if ("netty".equalsIgnoreCase(type)) {
            return NettySocketChannelPool.open(address);
//        } else {
//            return BioSocketChannelPool.open(address);
//        }

    }
}
