/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern.GUI;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author majd
 */
public class FacebookData implements UserData {
        String name;
        String Lastname;
        String email;
        String id;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getId() {
            return id;
        }
         @Override
    public String getLastname() {
return Lastname;}

    @Override
    public String getEmail() {
return email;    }

        @Override
        public String getImage() {
            return "http://graph.facebook.com/v2.4/" + id + "/picture";
        }

        @Override
        public void fetchData(String token, Runnable callback) {
            ConnectionRequest req;
            req = new ConnectionRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> parsed = parser.parseJSON(new InputStreamReader(input, "UTF-8"));
                    name = (String) parsed.get("name");
                    id = (String) parsed.get("id");
                    Lastname = (String) parsed.get("lastname");
                    email = (String) parsed.get("email");
                   
                }

                @Override
                protected void postResponse() {
                    callback.run();
                }

                @Override
                protected void handleErrorResponseCode(int code, String message) {
                    
                }
            };
            req.setPost(false);
            req.setUrl("https://graph.facebook.com/v2.4/me");
            req.addArgumentNoEncoding("access_token", token);
            NetworkManager.getInstance().addToQueue(req);
        }

   
}