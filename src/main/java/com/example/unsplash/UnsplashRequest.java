package com.example.unsplash;

public class UnsplashRequest {
   final String API = "https://api.unsplash.com";
   final String SEARCH = "/search/photos";
   final String CLIENT_ID = "client_id=v3o0jU3i0pFwqYkpj6tZRRUK7_Y3D_FyflsOBbpJ77g";

    public String search (String userQuery){
        String searchPhoto = API +  SEARCH + "?query=" + userQuery + "&" + CLIENT_ID;
        return searchPhoto;
    }

}
