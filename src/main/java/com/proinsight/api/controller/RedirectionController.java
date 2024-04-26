package com.proinsight.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectionController {

    @RequestMapping("/dev/**")
    public void redirectDev(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURL = request.getRequestURL().toString();
        String redirectURL = requestURL.replace("/dev", "");
        response.sendRedirect(redirectURL);
    }

    @RequestMapping("/prod/**")
    public void redirectProd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURL = request.getRequestURL().toString();
        String redirectURL = requestURL.replace("/prod", "");
        response.sendRedirect(redirectURL);
    }
}