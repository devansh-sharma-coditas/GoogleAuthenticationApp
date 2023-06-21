package com.Google.GoogleAuthenticationApp.controller;

import com.Google.GoogleAuthenticationApp.model.requestDto.RegisterRequestDto;
import com.Google.GoogleAuthenticationApp.model.responseDto.CustomResponse;
import com.Google.GoogleAuthenticationApp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GoogleController{
    @Autowired
    private UserService userService;


    @GetMapping("/")
    String homeController(){
        return "loginPage.html";
    }

    @GetMapping("/response")
    ResponseEntity<CustomResponse> successController(OAuth2AuthenticationToken authenticationToken) throws JsonProcessingException {
        return ResponseEntity.ok(userService.registerUserToDB(authenticationToken));
    }
    @PostMapping("/register")
    ResponseEntity<CustomResponse> registerController(@RequestBody RegisterRequestDto registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));
    }

}

