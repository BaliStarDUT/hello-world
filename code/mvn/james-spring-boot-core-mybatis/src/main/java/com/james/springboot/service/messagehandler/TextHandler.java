package com.james.springboot.service.messagehandler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by James Yang on 2017/6/29 0029 上午 11:19.
 */
public class TextHandler implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) throws WxErrorException {
        WxXmlOutMessage xmlOutMsg = null;
        String content = wxXmlMessage.getContent();
        if(StringUtils.isEmpty(content)){
            Date date2 = new Date();
            String msgText4 = "现在的时间是"+(date2.toString());
            xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText4).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
            return xmlOutMsg;
        }
        switch(content){
            case "blog":
                String msgText = "<a href=\"http://balistardut.github.io\">DX3906的博客</a>";
                xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
                break;
            case "你是谁":
                String msgText2 = "您好，我是杨振的公众号";
                xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText2).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
                break;
            case "date":
                Date date = new Date();
                String msgText3 = "现在的时间是"+(date.toString());
                xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText3).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
                break;
            case "picture":
                String imgMeidaId = "5I0urdYNfz0hLJSD2-HZdoHQG01hxqw-ZUnEUQEJZEdz7aR0yWsX9-jb6qw-E6dg";
                xmlOutMsg = WxXmlOutMessage.IMAGE().mediaId(imgMeidaId).build();
                break;
            case "music":
                xmlOutMsg = WxXmlOutMessage.MUSIC().title("黑默丁格")
                        .description("嗯，非常有趣").musicUri("http://lol.52pk.com/pifu/sounds/heimodingge/8.mp3")
                        .hQMusicUrl("http://lol.52pk.com/pifu/sounds/heimodingge/8.mp3")
                        .thumbMediaId("3RtWsjhJ6QtGTAJ8u0Hu_Xfe9wIrhhuMw5FRb_s25kwWl8I65e-50-y2LM5GvgvY")
                        .build();
                break;
            case "亚索":
                xmlOutMsg = WxXmlOutMessage.MUSIC().title("亚索")
                        .description("我的剑比什么都重要！除了美酒")
                        .musicUri("http://lol.52pk.com/pifu/sounds/yasuo/30.mp3")
                        .hQMusicUrl("http://lol.52pk.com/pifu/sounds/yasuo/30.mp3")
                        .thumbMediaId("3RtWsjhJ6QtGTAJ8u0Hu_Xfe9wIrhhuMw5FRb_s25kwWl8I65e-50-y2LM5GvgvY")
                        .build();
                break;
            case "提莫":
                xmlOutMsg = WxXmlOutMessage.MUSIC().title("提莫")
                        .description("提莫队长正在待命。")
                        .musicUri("http://lol.52pk.com/pifu/sounds/Teemo.mp3")
                        .hQMusicUrl("http://lol.52pk.com/pifu/sounds/Teemo.mp3")
                        .thumbMediaId("3RtWsjhJ6QtGTAJ8u0Hu_Xfe9wIrhhuMw5FRb_s25kwWl8I65e-50-y2LM5GvgvY")
                        .build();
                break;
            case "伊泽瑞尔":
                xmlOutMsg = WxXmlOutMessage.MUSIC().title("伊泽瑞尔")
                        .description("是时候表演真正的技术了")
                        .musicUri("http://lol.52pk.com/pifu/sounds/Ezreal.mp3")
                        .hQMusicUrl("http://lol.52pk.com/pifu/sounds/Ezreal.mp3")
                        .thumbMediaId("3RtWsjhJ6QtGTAJ8u0Hu_Xfe9wIrhhuMw5FRb_s25kwWl8I65e-50-y2LM5GvgvY")
                        .build();
                break;
            case "费德提克":
                xmlOutMsg = WxXmlOutMessage.MUSIC().title("费德提克")
                        .description("我知道你怕!")
                        .musicUri("http://lol.52pk.com/pifu/sounds/feidetike/7.mp3")
                        .hQMusicUrl("http://lol.52pk.com/pifu/sounds/feidetike/7.mp3")
                        .thumbMediaId("3RtWsjhJ6QtGTAJ8u0Hu_Xfe9wIrhhuMw5FRb_s25kwWl8I65e-50-y2LM5GvgvY")
                        .build();
                break;
            case "video":
                xmlOutMsg = WxXmlOutMessage.VIDEO().title("费德提克")
                        .description("我知道你怕!")
                        .mediaId("pQE42wN_NCgDuTC9s0ohWdlhp0h0qc1TPFnnve-z9mKqJ6bbfRqTlt2hB6jua-2u")
                        .build();
                break;
            case "new":
                xmlOutMsg = WxXmlOutMessage.TEXT()
                        .content("费德提克")
                        .build();
                break;
            default:
                Date date2 = new Date();
                String msgText4 = "现在的时间是"+(date2.toString());
                xmlOutMsg = WxXmlOutMessage.TEXT().content(msgText4).toUser(wxXmlMessage.getFromUserName()).fromUser(wxXmlMessage.getToUserName()).build();
                break;
        }
        return xmlOutMsg;
    }
}
