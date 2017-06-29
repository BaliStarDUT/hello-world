package com.james.springboot.service;

/**
 * Created by James Yang on 2017/6/27 0027 下午 3:45.
 */

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.Map;

/**
 * Demo 处理微信消息Handler
 * @author antgan
 */
public class DemoHandler implements WxMessageHandler {
    //wxMessage 消息 | context 上下文 | WxService API对象
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService wxService) throws WxErrorException {
        WxXmlOutMessage xmlOutMsg = null;
        //必须以build()作为结尾，否则不生效。
        if(wxMessage.getMsgType().equals(WxConsts.XML_MSG_TEXT)){
            xmlOutMsg = WxXmlOutMessage.TEXT().content("恭喜你中奖了").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
        }
        return xmlOutMsg;
    }
}
