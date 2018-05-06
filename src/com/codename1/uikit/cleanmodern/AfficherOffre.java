/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tarek
 */
public class AfficherOffre extends BaseForm {

    Form f;
    SpanLabel mb;

    public AfficherOffre(Resources res) {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Offre ");
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

        Label facebook = new Label();
        facebook.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label())
                        )
                )
        ));

        //  Form f = new Form("Offre", new BorderLayout());
        f = new Form("Offre", BoxLayout.y());
        mb = new SpanLabel();

        f.add(mb);

        AnnonceService Service = new AnnonceService();
        List<Annonce> list = new ArrayList<>();
        list = Service.testlist();

        for (Annonce e : list) {

            f.add(addButton(res.getImage("images.jpg"),  e.getTitre_annonce() ,res ,e.getId_annonce()));
 

        }

    }
   
    public Container addItem(Annonce t, Resources res) {

        Container cnt = new Container(BoxLayout.y());

        Button click = new Button(t.getTitre_annonce());

        cnt.add(click);
      //  cnt.add(bn);
        click.addActionListener((evt) -> {
            //  new AfficherOffreDetail(t.getId_annonce()).getF().show();
            new AfficherOffreDetail(t.getId_annonce(), res).getF().show();
        });
        

        return cnt;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    private Container addButton(Image img, String title ,Resources res , String id) {
       
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       Container cnt1 = new Container(BoxLayout.y());
       Container cnt2 = new Container(BoxLayout.x());
       
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       SpanLabel contenu = new SpanLabel();
       contenu.setUIID("NewsTopLine");
     
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,contenu,
                       BoxLayout.encloseX()
               ));
       cnt1.add(cnt);
       cnt1.add(cnt2);
       
       
       
         image.addActionListener((evt) -> {
           
           
           new AfficherOffreDetail(id,res).getF().show();
           
       });
       
       return (cnt1);
    
   }
    
    
    
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}