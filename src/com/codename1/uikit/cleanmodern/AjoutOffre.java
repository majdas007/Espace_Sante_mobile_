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
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.north;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.myapp.Entite.Annonce;
import com.codename1.uikit.cleanmodern.Service.AnnonceService;
import java.io.ByteArrayOutputStream;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class AjoutOffre extends BaseForm {
public String imagename;
    public AjoutOffre(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Publier Votre Offre");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 5) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 5);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        TextField Titre = new TextField();
        TextField Description = new TextField();
        TextField adresse = new TextField();
        TextField tel = new TextField();
        TextField image = new TextField();

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label())
                        )
                )
        ));

        Titre.setUIID("Titre");
        addStringValue("Titre", Titre);
        Description.setUIID("Description");
        addStringValue("Description", Description);
        adresse.setUIID("adresse");
        addStringValue("adresse", adresse);
        tel.setUIID("tel");
        addStringValue("telephone", tel);
        image.setUIID("image");
        addStringValue("image", image);
        //image img= theme.getImage("imagename");




//setLayout(new BorderLayout());
//         Toolbar tb1 = new Toolbar(true);
//        setToolbar(tb1);
//        tb1.setBackCommand("", e -> new WalkthruForm(res).show());
//
//        Container north = new Container(new FlowLayout(Component.CENTER));
//        north.setUIID("SignUpNorth");
//        Container north2 = new Container(new FlowLayout(Component.CENTER));
//        north2.setUIID("SignUpNorth");
//        int height1 = Display.getInstance().convertToPixels(15f);
//        int width1 = Display.getInstance().convertToPixels(17f);
//
//        
//        Button photoButton = new Button(res.getImage("profile-photo-button.png").fill(width1, height1));
//        photoButton.setUIID("ProfilePhoto");
//        photoButton.getStyle().setMargin(20, BOTTOM, LEFT, RIGHT);
//        north.addComponent(photoButton);
//        photoButton.addActionListener((ActionEvent e) -> {
//            String filePath = Capture.capturePhoto();
//            if (filePath != null) {
//                try {
//                    // String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg";
//                    Image img1 = Image.createImage(filePath);
//                    int height = Display.getInstance().convertToPixels(18f);
//                    int width = Display.getInstance().convertToPixels(20f);
//                    ImageViewer r = new ImageViewer(img1);
//
//                    // r.getImage().fill(width, height);
//                    Button photoButton2 = new Button(r.getImage().fill(width, height));
//                    photoButton2.setUIID("ProfilePhoto");
//                    photoButton2.getStyle().setMargin(20, BOTTOM, LEFT, RIGHT);
//
//                    north.clearClientProperties();
//                    north2.addComponent(photoButton2);
//                    this.add(BorderLayout.NORTH, north2);
//
//                    ImageIO imgIO = ImageIO.getImageIO();
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    imgIO.save(img1, out, ImageIO.FORMAT_PNG, 1);
//                    byte[] ba = out.toByteArray();
//                    String Imagecode = Base64.encode(ba);
////System.out.println("data :"+Imagecode);
//                    ConnectionRequest request = new ConnectionRequest();
//                    request.addResponseListener((NetworkEvent evt) -> {
//                        byte[] data = (byte[]) evt.getMetaData();
//                        imagename = new String(data);
//
//                    });
//                    System.out.println("aaaaaa");
//                    request.setPost(true);
//                    request.setHttpMethod("POST");
//                    request.addArgument("Image", Imagecode);
//                    request.setUrl("http://localhost/Mohamedname.php");
//                    NetworkManager.getInstance().addToQueueAndWait(request);
//
//                } catch (Exception ej) {
//                    ej.printStackTrace();
//                }
//
//            }
//
//            System.out.println(imagename);
//
//        });
        
        
        
      //////////////////////////////////  
      
      
        Button AJOUTER = new Button("Publier ");
        addStringValue("", AJOUTER);

        AJOUTER.addActionListener((evt) -> {

            if (Titre.getText().equals("")) {
                Dialog.show("champs vides", "Titre Manquant ", "ok", null);

            } else if (Description.getText().equals("")) {
                Dialog.show("champs vides", "Description Manquant ", "ok", null);

            } else if (adresse.getText().equals("")) {
                Dialog.show("champs vides", "adresse Manquant ", "ok", null);

            } else if (tel.getText().equals("")) {
                Dialog.show("champs vides", "telephone Manquant ", "ok", null);

            } else if (image.getText().equals("")) {
                Dialog.show("Champs vides", "Image Manquant ", "ok", null);

            } else {
                Annonce q = new Annonce(Titre.getText(), Description.getText(), adresse.getText(), Integer.valueOf(tel.getText()));

                AnnonceService Service = new AnnonceService();
                Service.addOffre(q);
            AfficherOffre h = new AfficherOffre(res);
                        h.getF().show();

//                    ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost/EspaceSanteWeb/web/app_dev.php/AjoutOffreMobile?" + "titre=" + q.getTitre_annonce() + "&description=" + q.getDesc_annonce() + "&adresse=" + q.getAddr_annonce() + "&tel=" + q.getTel_annonce() + imagename ;
//        con.setUrl(Url);
//
//        //System.out.println("tt");
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
                
                
                
                
            }

        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}