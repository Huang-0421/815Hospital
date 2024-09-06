package com.nine.backend.filter;
import com.alibaba.fastjson2.JSONObject;
import com.nine.backend.utils.JwtUtils;
import com.nine.backend.utils.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import java.io.IOException;

/**
 * @author Huang_ruijie
 * @version 1.0
 * @date 2024/7/22 13:23
 */
@Configuration
@WebFilter(urlPatterns = "/*")
public class filter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.contains("login") || url.contains("captcha") || url.contains("sendSms") || url.contains("sign")||url.contains("upload")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //从cookies中读取token
            Cookie[] cookies = request.getCookies();
            String token = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            if (!StringUtils.hasLength(token)) {
                Result result = Result.error("NOT_LOGIN");
                response.getWriter().write(JSONObject.toJSONString(result));
            }
            else {
                try {
                    JwtUtils.parseJWT(token);
                } catch (Exception e) {
                    Result result = Result.error("NOT_LOGIN");
                    response.getWriter().write(JSONObject.toJSONString(result));
                    return;
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}