package com.james.springboot.controller;

import com.james.springboot.service.messagehandler.*;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by James Yang on 2017/6/29 0029 下午 2:04.
 */
@RestController
public class WxController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 实例化 统一业务API入口
    private IService iService = new WxService();

    @RequestMapping(value = "/wx",method = RequestMethod.GET)
    void wx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 验证服务器的有效性
        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            out.print(echostr);
        }else {
            logger.warn("checkSignature "+"faild!");
        }
    }
    @RequestMapping(value = "/wx",method = RequestMethod.POST)
    void wxpost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 返回消息给微信服务器
        PrintWriter out = response.getWriter();

        // 创建一个路由器
        WxMessageRouter router = new WxMessageRouter(iService);
        try {
            // 微信服务器推送过来的是XML格式。
            WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
            logger.debug("收到的消息："+wx.toString());

            // 添加规则；这里的规则是所有消息都交给DemoMatcher处理，交给DemoInterceptor处理，交给DemoHandler处理
            // 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
            // end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
            router.rule().msgType(WxConsts.XML_MSG_TEXT).handler(new TextHandler())
                    .end();
            router.rule().msgType(WxConsts.XML_MSG_IMAGE).handler(new ImageHandler())
                    .end();
            router.rule().msgType(WxConsts.XML_MSG_LOCATION).handler(new LocationHandler())
                    .end();
            router.rule().msgType(WxConsts.XML_MSG_VOICE).handler(new VoiceHandler())
                    .end();
            router.rule().handler(new DefaultHandler())
                    .end();
            // 把消息传递给路由器进行处理
            WxXmlOutMessage xmlOutMsg = router.route(wx);
            if (xmlOutMsg != null){
                logger.debug("返回的响应："+xmlOutMsg.toString());
                out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}
