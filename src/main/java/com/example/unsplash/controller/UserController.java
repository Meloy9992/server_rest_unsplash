package com.example.unsplash.controller;

import com.example.unsplash.UnsplashRequest;
import com.example.unsplash.model.User;
import com.example.unsplash.parseJson.ParseQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/search")
    private ResponseEntity<?> searching() throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{searching}")
    private ResponseEntity<?> search(@PathVariable(name = "searching") String search) throws Exception {

        UnsplashRequest request = new UnsplashRequest();

        URL url = new URL(request.search(search));

        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input = reader.readLine();
        List<User> searching = new ParseQuery().getUsers(new ParseQuery().getResults(input));
        reader.close();
        return new ResponseEntity<>(searching, HttpStatus.OK);
    }
}
