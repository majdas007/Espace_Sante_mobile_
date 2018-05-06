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

import com.codename1.components.InfiniteProgress;
import com.codename1.components.SignatureComponent;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.Entite.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public User u = new User();

    public SignInForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> new WalkthruForm(res).show());
        setUIID("SignIn");

        //add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("password");
        username.setSingleLineTextArea(true);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");

        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        loginIcon.getStyle().setMargin(1, BOTTOM, 2, RIGHT);

        signIn.getStyle().setMargin(5, 0, 9, 9);
        //signUp.getStyle().setMargin(10, 0, LEFT, RIGHT);

        //signUp.addActionListener(e -> new SignUpForm(res).show());
        //signUp.setUIID("Link");
        // Label doneHaveAnAccount = new Label("Don't have an account?");
        password.getStyle().setMargin(5, BOTTOM, LEFT, RIGHT);
        SignatureComponent sig = new SignatureComponent();
        // SignatureComponent sig = new SignatureComponent();
//              addComponent(sig);

        sig.addActionListener((evt) -> {

            Image img = sig.getSignatureImage();

        });

        // add(sig);
        Container content2 = BoxLayout.encloseY(
                //  BorderLayout.center(sig),
                BorderLayout.center(username),
                BorderLayout.center(password)
        );
        content2.setScrollableY(true);
        content2.getStyle().setMargin(265, 0, 20, 20);
        add(BorderLayout.CENTER, content2);

        Container content = BoxLayout.encloseY(
                signIn
        );
        content.setScrollableY(true);

        // content.getStyle().setMargin(0, 0, LEFT, RIGHT);
        add(BorderLayout.SOUTH, content);
        //  add(BorderLayout.SOUTH, sig);
        signIn.requestFocus();
        signIn.addActionListener((ActionEvent e) -> {

            if (username.getText().equals("")) {
                Dialog.show("error", "Champs Vides ", "ok", null);

            } else if (password.getText().equals("")) {
                Dialog.show("error", "Champs Vides ", "ok", null);

            } else {

                Dialog dialog = new InfiniteProgress().showInifiniteBlocking();
                // Status status = ToastBar.getInstance().createStatus();
                //status.setMessage("coucouuo");
                //status.show();

                ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://localhost/EspaceSante/web/app_dev.php/mobilelogin/"+username.getText()+"/"+password.getText());
                con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        //   System.out.println(new String(con.getResponseData()));

                        u = getUser(new String(con.getResponseData()));

                        String ch = new String(con.getResponseData());
                        //System.out.println(ch);

                        if (ch.equals("false")) {
                            dialog.dispose();

                            // getListEtudiant(new String(con.getResponseData()));
                            System.out.println("ereuuuur");
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setMessage("Erreur, verifier vos parametres de connexion");
                            status.show();
                            // status.setProgress(20);
                            //status.clear();

                        } //System.out.println(Eshop.currentUser.getStatus());
                        else if (u.getStatus().equals("pending")) {
                            
                               Dialog dlg = new Dialog("votre compte est punding activer votre compte!");
                              Button active = new Button("active");
                               Button close = new Button("Fermer");
                               dlg.add(active);
                               active.addActionListener((e) -> {
                              
                                  
                                  
                   
                ConnectionRequest con = new ConnectionRequest();
                
                con.setUrl("http://localhost/EspaceSante/web/app_dev.php/mobileActivation/"+u.getId());
                                  
                       con.addResponseListener(new ActionListener<NetworkEvent>() {

                           @Override
                            public void actionPerformed(NetworkEvent evt) {
                        String ch = new String(con.getResponseData());
                                System.out.println(ch);
                                if (ch.equals("true")) {
                                  CleanModern.currentUser = u ;
                                    new AcceuilForm(res).show();
                                }
                            }
                        });
                        NetworkManager.getInstance().addToQueue(con);
                                  System.out.println(u.getId());
                              });
                               dlg.add(close);
                               dlg.show();
                              close.addActionListener((e) -> {
                                  new SignInForm(res).show();
                              });
                              
                              
                              
                               
                             
                                    
                            
//                            dialog.dispose();
//
//                            // getListEtudiant(new String(con.getResponseData()));
//                            System.out.println("ereuuuur");
//
//                            ToastBar.Status status = ToastBar.getInstance().createStatus();
//                            status.setMessage("Erreur, il faut activer votre compte");
//                        
//                            status.show();

                        } else {
                            //System.out.println(getUser(new String(con.getResponseData())));
                            CleanModern.currentUser = getUser(new String(con.getResponseData()));
                            System.out.println(CleanModern.currentUser);
                            dialog.dispose();

                            new AcceuilForm(res).show();
                            // new ActivateForm(res).show();
                            //new ProfileForm(res).show();

                            System.out.println("aaa");
                        }

                    }

                });

                NetworkManager.getInstance().addToQueue(con);

              
               
                // SmsSender.SendSMS("+21627170090",API_PHONE, Eshop.code+"");

                // status.clear();
//        Message m = new Message("Body of messagccccccccce");
////m.getAttachments().put(textAttachmentUri, "text/plain");
////m.getAttachments().put(imageAttachmentUri, "image/png");

            }
        });
        // signUp.requestFocus();
        //signUp.addActionListener(e -> new AcceuilForm(res).show());
    }

    public User getUser(String json) {
        //ArrayList<User> listUser = new ArrayList<>();
        User e = new User();
        try {

            //Dialog.show(json, json, json, json);
            JSONParser j = new JSONParser();

            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
            // System.out.println(list.get(0));
            for (Map<String, Object> obj : list) {
                e.setId((int) Float.parseFloat(obj.get("id").toString()));
               
                e.setFirstname(obj.get("nom").toString());
                e.setLastname(obj.get("prenom").toString());
                e.setEmail(obj.get("email").toString());
                e.setAdress(obj.get("adresse").toString());
                e.setTelephone(obj.get("telephone").toString());
                e.setPassword(obj.get("password").toString());

            }

        } catch (IOException ex) {
        }
        return e;

    }

}