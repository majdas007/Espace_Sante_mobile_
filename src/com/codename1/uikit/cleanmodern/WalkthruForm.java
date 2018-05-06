/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.Entite.User;
import com.codename1.uikit.cleanmodern.GUI.FacebookData;
//import Entites.User;
//import GUI.FacebookData;



/**
 * Swiping thru tutorial
 *
 * @author Shai Almog
 */
public class WalkthruForm extends Form {
        private LoginCallback loginCallback;
          public FacebookData data = new FacebookData();
          private String fullName;
          public String    tokenPrefix ;

private String uniqueId;
private String imageURL;
private  String email;

    public LoginCallback getLoginCallback() {
        return loginCallback;
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }

    public FacebookData getData() {
        return data;
    }

    public void setData(FacebookData data) {
        this.data = data;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public WalkthruForm(Resources res) {
            super(new BorderLayout());
                    getTitleArea().setUIID("Container");

        setUIID("First");
        Button signIn = new Button(" Login ");
       // signIn.setUIID("ButtonF");
        Button signUp = new Button("Inscription");
        Button facebook =new Button("        ");
        facebook.setUIID("fb");
        facebook.getStyle().setMargin(TOP, BOTTOM, 120, RIGHT);
          signIn.getStyle().setMargin(7, 1, 9, 9);
                     //signUp.pressed();
           signUp.setUIID("ButtonF");
           signUp.getStyle().setMargin(0, 9, 9, 9);
           
         //signUp.setUIID("ButtonF");
          Container content = BoxLayout.encloseY(
             
                signIn,

                signUp ,
                facebook
                );
        
   

          //content.getStyle().setMargin(50, 50, 49, RIGHT);
        content.setScrollableY(false);
        add(BorderLayout.SOUTH, content);
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signIn.addActionListener(e -> new SignInForm(res).show());
        


        
        facebook.addActionListener((ActionEvent e)->{
                tokenPrefix = "facebook";

            String clientId = "2117350995153768";
            
            //2117350995153768
String redirectURI = "https://www.codenameone.com/";
   
    String clientSecret = "5e72e5a4479934b5f9700e2df1eec57d";
    Login fb = FacebookConnect.getInstance();
 fb.setClientId(clientId);
 fb.setRedirectURI(redirectURI);
 fb.setClientSecret(clientSecret);
 //Sets a LoginCallback listener
     CleanModern.currentUser=doLogin(fb, new FacebookData(), false, res);
         
            


});
    }

    private User doLogin(Login fb, FacebookData facebookData, boolean b, Resources res) {
User e =new User();
    

    fb.setCallback(new LoginCallback() {
        @Override
        public void loginFailed(String errorMessage) {
            Dialog.show("Error Logging In", "There was an error logging in: " + errorMessage, "OK", null);
        }

        @Override
        public void loginSuccessful() {
            // when login is successful we fetch the full data
            data.fetchData(fb.getAccessToken().getToken(), ()-> {
                // we store the values of result into local variables
                uniqueId = data.getId();
                fullName = data.getName();
                imageURL = data.getImage();
                email =data.getEmail();
                System.out.println(imageURL);
//                Eshop.currentUser.setId(Integer.parseInt(getUniqueId()));
                CleanModern.currentUser.setFirstname(getFullName());
                CleanModern.currentUser.setLastname(getFullName());
                CleanModern.currentUser.setEmail(getEmail());
                CleanModern.currentUser.setPassword("facebook");
                CleanModern.currentUser.setAvatar(getImageURL());
                new AcceuilForm(res).show();
                System.out.println(CleanModern.currentUser);
           
                

                // we then store the data into local cached storage so they will be around when we run the app next time
                Preferences.set("fullName", fullName);
                Preferences.set("uniqueId", uniqueId);
                Preferences.set("imageURL", imageURL);
                
             //   Preferences.set(tokenPrefix + "token", lg.getAccessToken().getToken());

                // token expiration is in seconds from the current time, we convert it to a System.currentTimeMillis value so we can
                // reference it in the future to check expiration
              // Preferences.set(tokenPrefix + "tokenExpires", tokenExpirationInMillis(fb.getAccessToken()));
               // showContactsForm(data);
            });
        }
        
    });
    fb.doLogin();
    
    
         e.setFirstname(getFullName());
                e.setLastname(getFullName());
                e.setEmail(getEmail());
                e.setPassword("facebook");
                e.setSolde(0);
                e.setAvatar("avatar.png");
    return e;
    }
}