package com.james.springboot.service;

/**
 * Created by James Yang on 2017/6/27 0027 下午 3:44.
 */

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageInterceptor;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.Map;

/**
 * Demo 拦截器，可以做身份验证，权限验证等操作。
 * @author antgan
 */
public class DemoInterceptor implements WxMessageInterceptor {
    public boolean intercept(WxXmlMessage wxMessage, Map<String, Object> context, IService wxService)
            throws WxErrorException {
        //可以使用wxService的微信API方法
        //可以在Handler和Interceptor传递消息，使用context上下文
        //可以实现自己的业务逻辑

        //这里就不编写验证关注三天以上的用户了
//        if(/*用户关注时长大于3天*/){
//            return true;
//        }
        return true;
    }
}