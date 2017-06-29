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
 * Created by James Yang on 2017/6/29 0029 上午 11:45.
 */
public class ImageHandler implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) throws WxErrorException {
        WxXmlOutMessage xmlOutMsg = null;
        String mediaId = wxXmlMessage.getMediaId();
        if(StringUtils.isEmpty(mediaId)){
            Date date = new Date();
            String msgText = "无法获取图片素材。\n现在的时间是"+(date.toString());
            xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
            return xmlOutMsg;
        }else {
            xmlOutMsg = WxXmlOutMessage.IMAGE().mediaId(mediaId).build();
        }
        return xmlOutMsg;
    }
}
