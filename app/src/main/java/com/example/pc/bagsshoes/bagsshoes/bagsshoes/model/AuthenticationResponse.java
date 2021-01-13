package com.example.pc.bagsshoes.bagsshoes.bagsshoes.model;

public class AuthenticationResponse{
    private String token;
    private String error;
    public AuthenticationResponse() {

    }

    public AuthenticationResponse(String token, String error){
        this.token = token;
        this.error = error;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void setError(String error){
        this.error = error;
    }

    public String getError(){
        return this.error;
    }
}
