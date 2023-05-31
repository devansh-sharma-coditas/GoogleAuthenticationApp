package com.Google.GoogleAuthenticationApp.controller;

import com.Google.GoogleAuthenticationApp.entities.UserInfo;
import com.Google.GoogleAuthenticationApp.model.responseDto.CustomResponse;
import com.Google.GoogleAuthenticationApp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class GoogleController{
    @Autowired
    private UserService userService;


    @GetMapping("/")
    String homeController(){
        return "loginPage.html";
    }

    @GetMapping("/register")
    @ResponseBody
    ResponseEntity<CustomResponse> successController(OAuth2AuthenticationToken authenticationToken) throws JsonProcessingException {
        return ResponseEntity.ok(userService.registerUser(authenticationToken));
    }
}

