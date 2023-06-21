package com.Google.GoogleAuthenticationApp.service;

import com.Google.GoogleAuthenticationApp.model.requestDto.RegisterRequestDto;
import com.Google.GoogleAuthenticationApp.model.responseDto.CustomResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface UserService {
    CustomResponse registerUserToDB(OAuth2AuthenticationToken authenticationToken) throws JsonProcessingException;
}
