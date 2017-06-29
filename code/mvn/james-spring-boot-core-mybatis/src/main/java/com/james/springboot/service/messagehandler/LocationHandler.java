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
 * Created by James Yang on 2017/6/29 0029 下午 12:42.
 */
public class LocationHandler  implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) throws WxErrorException {
        WxXmlOutMessage xmlOutMsg = null;

        String msgType = wxXmlMessage.getMsgType();
        Double Location_X = wxXmlMessage.getLocationX();
        Double Location_Y = wxXmlMessage.getLocationY();
        Double Scale = wxXmlMessage.getScale();
        String Label = wxXmlMessage.getLabel();
        String content = "经度："+Location_X+"\n"
                + "纬度："+Location_Y+"\n"
                + "地图缩放大小："+Scale+"\n"
                + "位置信息："+Label+"\n";

        if(StringUtils.isEmpty(msgType)){
            Date date = new Date();
            String msgText = "无法获取素材类型:"+msgType+"。\n现在的时间是"+(date.toString());
            xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
            return xmlOutMsg;
        }
        xmlOutMsg = WxXmlOutMessage.TEXT().content(content).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
        return xmlOutMsg;
    }
}
