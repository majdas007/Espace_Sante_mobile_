/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
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
public class AfficherEventDetail extends BaseForm {

    Form f;
    SpanLabel lb;
    SpanLabel lb1;
    SpanLabel lb2;
    Button btnd;
    Button ret;
    Button bn;

    public AfficherEventDetail(String id, Resources res) {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Detail Evenement ");
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

        f = new Form("Evenement");
        lb = new SpanLabel("");
        lb1 = new SpanLabel("");
        lb2 = new SpanLabel();

        AnnonceService service = new AnnonceService();

        List<Annonce> list = new ArrayList<>();
        list = service.getListDetE(id);
        for (Annonce e : list) {
            f.add(addContact(e, id, id, res));

        }
//        f.getToolbar().addCommandToRightBar("back", null, (ev)->{
//          AfficherEvent h=new AfficherEvent(res);
//          h.getF().show();
//          });
 
    }

//       lb.setText(list.get(0).getTitre_annonce());
    public Container addContact(Annonce c, String nom, String desc, Resources res) {

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100, 100), true);
        URLImage i = URLImage.createToStorage(placeholder, c.getImg_annonce(), "http://localhost/EspaceSanteWeb/web/image/" + c.getImg_annonce());
        ImageViewer image = new ImageViewer(i);

        Label ltitre = new Label("titre : " + c.getTitre_annonce());
        Label ldesc = new Label("Adresse Mail : " + c.getDesc_annonce());
        Label ladr = new Label("Adresse : " + c.getAddr_annonce());
        Button confirm = new Button("confirm edit");
        Button annuler = new Button("Annuler");

        btnd = new Button("Modifier Event");
        ret = new Button("retour");
        bn = new Button("suprimer");
        Button Contact = new Button("Contacter");

        Label titre = new Label("titre :");
        TextField a = new TextField("");

        Label AdresseM = new Label("Adresse Mail:");
        TextField b = new TextField("Adresse Mail : ");

        Label Adresse = new Label("Adresse:");
        TextField cc = new TextField("Adresse : ");

        Label imag = new Label("image:");
        TextField d = new TextField("image");

        a.setText(c.getTitre_annonce());
        b.setText(c.getDesc_annonce());
        cc.setText(c.getAddr_annonce());
        d.setText(c.getImg_annonce());

        // a.setText(c.getTitre_annonce());
        Dialog dlg = new Dialog("edit");

        dlg.add(titre);
        dlg.add(a);
        dlg.add(AdresseM);
        dlg.add(b);
        dlg.add(Adresse);
        dlg.add(cc);
        dlg.add(imag);
        dlg.add(d);

        dlg.add(confirm);
        dlg.add(annuler);

//        f.getToolbar().addCommandToRightBar("back", null, (ev)->{AfficherOffreDetail h=new AfficherOffreDetail(c.getId_annonce(), res);
//          h.getF().show();
//          });
        annuler.addActionListener((l) -> {
            {
                AfficherEventDetail h = new AfficherEventDetail(c.getId_annonce(), res);
                h.getF().show();

            }
        });
        confirm.addActionListener((evt) -> {
            AnnonceService ann = new AnnonceService();
            ann.editEvent(c.getId_annonce(), a.getText(), b.getText(), cc.getText(), d.getText());
            new AfficherEventDetail(c.getId_annonce(), res).getF().show();

        });
        btnd.addActionListener((l) -> {
            dlg.show();

        });
        ret.addActionListener((l) -> {
            AfficherEvent h = new AfficherEvent(res);
            h.getF().show();

        });

        bn.addActionListener((evt) -> {
            AnnonceService abb = new AnnonceService();
            abb.deleterepE(c.getId_annonce());
            AfficherEvent b1 = new AfficherEvent(res);
            b1.getF().show();

        });

        //  Label ltype = new Label("question :" + c.getQuestion());
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cnt1 = new Container(BoxLayout.x());
        //image img= theme.getImage("imagename");
        cnt.add(image);
        cnt.add(ltitre);
        cnt.add(ldesc);
        cnt.add(ladr);
        Contact.addActionListener((evt) -> {
            Message m = new Message("Body of message");
            Display.getInstance().sendMessage(new String[]{c.getDesc_annonce()}, "Subject of message", m);
        });
        cnt1.add(btnd);
        cnt1.add(bn);
        cnt1.add(Contact);
        cnt.add(cnt1);
        return cnt;
        
         
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}