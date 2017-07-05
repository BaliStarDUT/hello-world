package com.james.springboot.service.messagehandler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by James Yang on 2017/6/29 0029 下午 12:36.
 */
public class VoiceHandler  implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) throws WxErrorException {
        WxXmlOutMessage xmlOutMsg = null;
        String mediaId = wxXmlMessage.getMediaId();
        String recognition = wxXmlMessage.getRecognition();
        String msgType = wxXmlMessage.getMsgType();
        if(StringUtils.isEmpty(mediaId) || StringUtils.isEmpty(recognition)){
            Date date = new Date();
            String msgText = "素材类型:"+msgType+"。\n现在的时间是"+(date.toString());
            xmlOutMsg = WxXmlOutMessage.TEXT()
                    .content(msgText)
                    .toUser(wxXmlMessage.getFromUserName())
                    .fromUser(wxXmlMessage.getToUserName()).build();
            return xmlOutMsg;
        }
        xmlOutMsg = WxXmlOutMessage.TEXT()
                .content("你是说："+recognition)
                .toUser(wxXmlMessage.getFromUserName())
                .fromUser(wxXmlMessage.getToUserName()).build();
        return xmlOutMsg;
    }
}
