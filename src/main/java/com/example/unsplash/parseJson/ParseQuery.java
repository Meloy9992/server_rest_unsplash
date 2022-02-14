package com.example.unsplash.parseJson;

import com.example.unsplash.model.Image;
import com.example.unsplash.model.User;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


import java.util.ArrayList;
import java.util.List;

public class ParseQuery {
    private JSONObject jsonObject;

    public JSONArray getResults(String urls) throws ParseException {
        jsonObject = (JSONObject) new JSONParser().parse(urls);
        JSONArray results = (JSONArray) jsonObject.get("results");
        return results;
    }

    public List<User> getUsers(JSONArray results){
        ArrayList<User> users = new ArrayList<>();
        for (int i=0; i<results.size(); i++){
            JSONObject information = (JSONObject) results.get(i);
            Image image = createImage(information);
            User user = new User();
            JSONObject jsonUser = (JSONObject) information.get("user");
            user.setId(isNull(jsonUser.get("id")));
            user.setBio(isNull(jsonUser.get("bio")));
            user.setImage(image);
            user.setUserName(isNull(jsonUser.get("username")));
            user.setName(isNull(jsonUser.get("first_name")));
            user.setLastName(isNull(jsonUser.get("last_name")));
            user.setLocation(isNull(jsonUser.get("location")));
            users.add(user);
        }
        return users;
    }

    private String isNull(Object data){
        String dataString = "null";
        if (data==null){
            dataString = "null";
        }else {
            dataString = data.toString();
        }
        return dataString;
    }

    private Image createImage(JSONObject information){
        JSONObject urls = (JSONObject) information.get("urls");
        return new Image(isNull(information.get("id")), Integer.parseInt(isNull(information.get("width"))), Integer.parseInt(isNull(information.get("height"))), isNull(information.get("description")), isNull(information.get("alt_description")), isNull(urls.get("raw")), isNull(urls.get("full")));
    }
}
