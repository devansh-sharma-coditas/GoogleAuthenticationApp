package com.Google.GoogleAuthenticationApp.service;

import com.Google.GoogleAuthenticationApp.entities.UserInfo;
import com.Google.GoogleAuthenticationApp.model.responseDto.CustomResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.security.Principal;

public interface UserService {
    CustomResponse registerUser(OAuth2AuthenticationToken authenticationToken) throws JsonProcessingException;
}
