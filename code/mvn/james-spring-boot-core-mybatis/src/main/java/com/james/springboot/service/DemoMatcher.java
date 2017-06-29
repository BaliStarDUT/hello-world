package com.james.springboot.service;

/**
 * Created by James Yang on 2017/6/27 0027 下午 3:43.
 */

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;

/**
 * Demo 简单的匹配器，可以用于更加复杂的消息验证操作
 * @author antgan
 */
public class DemoMatcher implements WxMessageMatcher {
    //答案是smart，如果匹配smart返回true；反之，false。
    public boolean match(WxXmlMessage message) {
        if(message.getContent().equals("smart")){
            return true;
        }
        return false;
    }
}