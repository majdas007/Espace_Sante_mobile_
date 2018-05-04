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
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.Entite.Question;
import com.codename1.uikit.cleanmodern.Entite.Reponse;
import com.codename1.uikit.cleanmodern.GUI.QuestionUi;
import com.codename1.uikit.cleanmodern.GUI.StatApi;
import com.codename1.uikit.cleanmodern.Service.QuestionService;
import com.codename1.uikit.cleanmodern.Service.ReponseService;
import java.util.ArrayList;
import java.util.List;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class StatUI extends BaseForm {
    private ComboBox choice ;

    public StatUI(Resources res ) {
        super("Newsfeed", BoxLayout.y());
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Statistiques");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
              ReponseService ReponseService =new ReponseService();

      tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 5) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 5);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            
                            FlowLayout.encloseCenter(
                                new Label())
                    )
                )
        ));
      
         
         
      
        Container cnt  = new StatApi().createPieChartForm();
        addStringValue("",cnt);
      
    }
    


    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
      public Container addItem (Image image,Reponse r,String id , Resources res )            
    { 
        int height = Display.getInstance().convertToPixels(2.5f);
       int width = Display.getInstance().convertToPixels(2);
       image.fill(width, height);
       Container cnt=new Container(BoxLayout.y());
       Dialog dlg1 = new Dialog("Modifier Reponse");
       TextArea editreponse = new TextArea(10, 15, 10);
        Button modifier = new Button("Modifier");
        Button annuler = new Button("annuler");
        dlg1.add(editreponse);    
        dlg1.add(modifier);
        dlg1.add(annuler);
      annuler.addActionListener((evt) -> {
          new ReponseUI(res,id).show();
      });
        
        
        modifier.addActionListener((evt) -> {
            ReponseService rep = new ReponseService();
            
            if(editreponse.getText().equals(""))
            {
            Dialog.show("error","Champs Vides ","ok", null);
            }
            else {
                rep.editrep(r.getId_rep(), editreponse.getText());
                new ReponseUI(res,id).show();
            }
            
            
            
        });
        
       
        
        
        
        
        
        
        editreponse.setText(r.getContenu_rep());
//        annuler.addActionListener((evt) -> {
//            this.getF().show();
//        });
        
        Container cnt1=new Container(BoxLayout.y());
        Container cnt2=new Container(BoxLayout.x());
        Container cnt3= new Container(BoxLayout.x());
        Button edit = new Button ("Edit");
        Button delete = new Button ("delete");
        
        delete.addActionListener((evt) -> {
     ReponseService rep = new ReponseService();
     rep.deleterep(r.getId_rep());
     new ReponseUI(res,id).show();

        });
       
       cnt3.add(edit);
       cnt3.add(delete);
       edit.addActionListener((evt) -> {
           dlg1.show();
       });


        SpanLabel lblid = new SpanLabel(r.getContenu_rep());
     
       cnt1.add(lblid);
      
       cnt2.add(image);
       cnt2.add(cnt1);
       cnt.add(cnt2);
       cnt.add(cnt3);
        return cnt;
    }
}
