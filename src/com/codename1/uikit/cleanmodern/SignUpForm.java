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

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SignatureComponent;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;

import java.io.ByteArrayOutputStream;



/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public String imagename;

    public SignUpForm(Resources res) {

        this.setUIID("signUp");
        getTitleArea().setUIID("Container");
       


        setLayout(new BorderLayout());
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setBackCommand("", e -> new WalkthruForm(res).show());

        Container north = new Container(new FlowLayout(Component.CENTER));
        north.setUIID("SignUpNorth");
        Container north2 = new Container(new FlowLayout(Component.CENTER));
        north2.setUIID("SignUpNorth");
        int height1 = Display.getInstance().convertToPixels(15f);
        int width1 = Display.getInstance().convertToPixels(17f);

        Button photoButton = new Button(res.getImage("user.png").fill(width1, height1));
        photoButton.setUIID("ProfilePhoto");
        photoButton.getStyle().setMargin(20, BOTTOM, LEFT, RIGHT);
        north.addComponent(photoButton);
        photoButton.addActionListener((ActionEvent e) -> {
            String filePath = Capture.capturePhoto();
            if (filePath != null) {
                try {
                    // String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg";
                    Image img = Image.createImage(filePath);
                    int height = Display.getInstance().convertToPixels(18f);
                    int width = Display.getInstance().convertToPixels(20f);
                    ImageViewer r = new ImageViewer(img);

                    // r.getImage().fill(width, height);
                    Button photoButton2 = new Button(r.getImage().fill(width, height));
                    photoButton2.setUIID("ProfilePhoto");
                    photoButton2.getStyle().setMargin(20, BOTTOM, LEFT, RIGHT);

                    north.clearClientProperties();
                    north2.addComponent(photoButton2);
                    this.add(BorderLayout.NORTH, north2);

                    ImageIO imgIO = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    imgIO.save(img, out, ImageIO.FORMAT_PNG, 1);
                    byte[] ba = out.toByteArray();
                    String Imagecode = Base64.encode(ba);
//System.out.println("data :"+Imagecode);
                    ConnectionRequest request = new ConnectionRequest();
                    request.addResponseListener((NetworkEvent evt) -> {
                        byte[] data = (byte[]) evt.getMetaData();
                        imagename = new String(data);

                    });
                    System.out.println("aaaaaa");
                    request.setPost(true);
                    request.setHttpMethod("POST");
                    request.addArgument("Image", Imagecode);
                    request.setUrl("http://localhost/Mohamedname.php");
                    NetworkManager.getInstance().addToQueueAndWait(request);

                } catch (Exception ej) {
                    ej.printStackTrace();
                }

            }

            System.out.println(imagename);

        });

        Button signUp = new Button("Inscription");
        //signUp.pressed();
        //signUp.setUIID("v");
        // Container north = new Container(new FlowLayout(Component.CENTER));
        this.addComponent(BorderLayout.NORTH, north);
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField username = new TextField();
        center.addComponent(username);
        username.setUIID("text");

        username.setHint("Uername");

        Container row1 = new Container(new GridLayout(1, 2));
        TextField firstName = new TextField();

        firstName.setUIID("text");
        firstName.setHint("Nom");
        TextField lastName = new TextField();
        lastName.setUIID("text");

        lastName.setHint("Prénom");
        row1.addComponent(firstName);
        row1.addComponent(lastName);
        center.addComponent(row1);
        center.setScrollableY(true);

        TextField email = new TextField();
        center.addComponent(email);
        email.setUIID("text");

        email.setHint("Adresse mail");

        TextField password = new TextField();
        password.setConstraint(TextField.PASSWORD);
        password.setUIID("text");
        password.setHint("Mot de passe");
        center.addComponent(password);

        Container row4 = new Container(new BorderLayout());
        Label code = new Label("+216");
        code.setUIID("text");
        row4.addComponent(BorderLayout.WEST, code);

        TextField phoneNumber = new TextField();
        phoneNumber.setHint("Numero de télephone");
        phoneNumber.setUIID("text");
        row4.addComponent(BorderLayout.CENTER, phoneNumber);
Container row5 = new Container(new BorderLayout());
    SignatureComponent sig = new SignatureComponent();
                 // SignatureComponent sig = new SignatureComponent();
//              addComponent(sig);

   sig.addActionListener((evt)-> {
  
   Image img = sig.getSignatureImage();

});
//   add(sig);
//   row5.addComponent(BorderLayout.CENTER, sig);
        center.addComponent(row4);
        //center.addComponent(row5);
        center.getStyle().setMargin(30, BOTTOM, 10, 10);

        this.addComponent(BorderLayout.CENTER, center);

        this.addComponent(BorderLayout.SOUTH, signUp);

        signUp.addActionListener((ActionEvent e) -> {
//             if(firstName.getText().equals("") || lastName.getText().equals("") || password.getText().equals("") || phoneNumber.getText().equals(""))
//                {
//                   Dialog.show("error","Champs Vides ","ok", null);
//                
//                }
//             else {
//            
            
            
            Dialog dialog = new InfiniteProgress().showInifiniteBlocking();

            ConnectionRequest con = new ConnectionRequest();
           
            con.setUrl("http://localhost/EspaceSante/web/app_dev.php/mobileregistration/omr/majd/omar/11111/aaa.omar@esprit.tn/889856/aaaaaaaaa");
            con.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
                    //   System.out.println(new String(con.getResponseData()));

                    String ch = new String(con.getResponseData());
                    if (ch.equals("false")) {
                        dialog.dispose();

                        System.out.println("nooooo");
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setMessage("Nom d'utilisateur existe déja");
                        status.show();

                    } else {
                        dialog.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setMessage("votre inscription est bien faite");
                        status.setProgress(20);
                       
                       // Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                       // SmsSender.SendSMS("+21627170090", API_PHONE, Eshop.code + "");
                        new SignInForm(res).show();

                    }

                }
            
            });
             NetworkManager.getInstance().addToQueue(con);
//             }
           

        });
        
    }
                
}