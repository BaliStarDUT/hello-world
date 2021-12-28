package com.james.springboot.web.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * author: yang
 * datetime: 2021/9/9 14:59
 */

public class AuthIntercepter extends HandlerInterceptorAdapter {

    private static final String USER_SESSION_ACCOUNT = "user";
    private static Logger logger = LoggerFactory.getLogger(AuthIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String user_name = (String) session.getAttribute(USER_SESSION_ACCOUNT);
        logger.info("Login user:{}",user_name);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PermissionsRequired permissions = handlerMethod.getMethodAnnotation(PermissionsRequired.class);
        if(Objects.isNull(permissions)){
            return true;
        }
        String url = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
