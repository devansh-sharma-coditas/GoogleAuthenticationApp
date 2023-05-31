package com.Google.GoogleAuthenticationApp.model.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private Object data;
    private int statusCode;
    private String status;
    private String message;
}
