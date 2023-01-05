package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllInterceptor implements HandlerInterceptor {
//    在控制器方法前执行,返回true放行，false拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("在控制器方法前执行");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
//在控制器方法后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("在控制器方法后执行");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
//在渲染完视图后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("在渲染视图后执行");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
