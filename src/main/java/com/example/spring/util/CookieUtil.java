package com.example.spring.util;


import com.example.spring.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CookieUtil {

  private AppProperties appProperties;

  public void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setSecure(secure);
    cookie.setHttpOnly(true);
    cookie.setMaxAge(maxAge);
    cookie.setDomain(appProperties.getDomain());
    cookie.setPath("/");
    httpServletResponse.addCookie(cookie);
  }

  public void clear(HttpServletResponse httpServletResponse, String name) {
    Cookie cookie = new Cookie(name, null);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(0);
    cookie.setDomain(appProperties.getDomain());
    httpServletResponse.addCookie(cookie);
  }

  public String getValue(HttpServletRequest httpServletRequest, String name) {
    Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
    return cookie != null ? cookie.getValue() : null;
  }
}
