package com.example.api.commons;


import com.example.api.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommon {
    RestTemplate restTemplate;

    public AuthCommon(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
public UserDto validateToken(String tokenValue){
        //call user service
    ResponseEntity<UserDto> responseEntity=restTemplate.getForEntity("http://localhost:4141/users/validate/"+tokenValue, UserDto.class);
    if(responseEntity.getBody()==null)return null;
    return responseEntity.getBody();
}
}
