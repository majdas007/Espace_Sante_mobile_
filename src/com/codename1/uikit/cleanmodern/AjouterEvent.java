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
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entite.Annonce;
import com.codename1.uikit.cleanmodern.Service.AnnonceService;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class AjouterEvent extends BaseForm {

    public AjouterEvent(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Publier Votre Evenement");
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
                Service.addEvent(q);
                AfficherEvent h = new AfficherEvent(res);
                        h.getF().show();
            }

        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}