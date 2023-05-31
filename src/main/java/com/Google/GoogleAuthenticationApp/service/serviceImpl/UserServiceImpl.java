package com.Google.GoogleAuthenticationApp.service.serviceImpl;

import com.Google.GoogleAuthenticationApp.entities.UserInfo;
import com.Google.GoogleAuthenticationApp.model.responseDto.CustomResponse;
import com.Google.GoogleAuthenticationApp.repository.UserRepository;
import com.Google.GoogleAuthenticationApp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.awt.print.Printable;
import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public CustomResponse registerUser(OAuth2AuthenticationToken authenticationToken) throws JsonProcessingException {
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(gson.toJson(authenticationToken));
        System.out.println(node);
        try {
            UserInfo user = null;
            String loginVia = node.get("authorizedClientRegistrationId").textValue();
            if (loginVia.equalsIgnoreCase("google")) {
                user = new UserInfo();
                user.setEmail(node.get("principal").get("attributes").get("email").textValue());
                user.setProfilePath(node.get("principal").get("attributes").get("picture").textValue());
                user.setFirstName(node.get("principal").get("attributes").get("given_name").textValue());
                user.setLastName(node.get("principal").get("attributes").get("family_name").textValue());
                user.setLoginVia("GOOGLE");
            } else if (node.get("authorizedClientRegistrationId").textValue().equalsIgnoreCase("github")) {
                user = new UserInfo();
                user.setEmail(node.get("principal").get("attributes").get("email").textValue());
                user.setProfilePath(node.get("principal").get("attributes").get("avatar_url").textValue());
                user.setLoginVia("GITHUB");
                user.setFirstName(node.get("principal").get("attributes").get("name").textValue().split(" ")[0]);
                user.setLastName("NOT FOUND ");

            } else if (node.get("authorizedClientRegistrationId").textValue().equalsIgnoreCase("facebook")) {
                user = new UserInfo();
//                 facebook don't provide email
//                 for now using principal.attributes.id as email because email can't be null
                user.setEmail(node.get("principal").get("attributes").get("id").textValue()+"@gmail.com");
//                user.setProfilePath(node.get("principal").get("attributes").get("avatar_url").textValue());
                user.setLoginVia("FACEBOOK");
                user.setFirstName(node.get("principal").get("attributes").get("name").textValue().split(" ")[0]);
                user.setLastName("NOT FOUND ");
            }
            return new CustomResponse(
                    userRepository.save(user),
//                authenticationToken,
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    "USER REGISTERED SUCCESSFULLY VIA "+loginVia+" !"
            );
        }catch (Exception e){
            return new CustomResponse(
                    authenticationToken,
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    "DATA NOT FOUND !"
            );
        }
    }
}
