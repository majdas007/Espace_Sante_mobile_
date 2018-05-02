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
import com.codename1.uikit.cleanmodern.Service.QuestionService;
import com.codename1.uikit.cleanmodern.Service.ReponseService;
import java.util.ArrayList;
import java.util.List;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ObjetsForm1 extends BaseForm {

    public ObjetsForm1 (Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Reponses");
        getContentPane().setScrollVisible(false);
         Label lb = new Label("");
         Button add = new Button("Repondre");
               addStringValue("", add);

        Dialog dlg = new Dialog("Ajouter Question");
         TextArea edittext = new TextArea(15, 15, 10);
       Button confirm = new Button("ajouter ");
       Button cancel = new Button("annuler");
       dlg.add(edittext);
       dlg.add(confirm);
        add.addActionListener((evt) -> {
           dlg.show();
       });
        ReponseService ReponseService =new ReponseService();

        
         List <Reponse> list = new ArrayList<>();
        list = ReponseService.getrep("3");
       
           for( Reponse e : list)
        {
            addStringValue("",addItem(e));
        }
         
         
         
         
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        

        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 5) {
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


      


    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
       public Container addItem (Reponse r )            
    {
       Container cnt=new Container(BoxLayout.y());

        Container cnt1=new Container(BoxLayout.y());
        Container cnt2=new Container(BoxLayout.x());
        Container cnt3= new Container(BoxLayout.x());
        Button edit = new Button ("Edit");
        Button delete = new Button ("delete");
       cnt3.add(edit);
       cnt3.add(delete);


        Label lblid = new Label(r.getContenu_rep());
     Label lbldesc = new Label(r.getId_rep());
     
        
     
      
       cnt1.add(lblid);
      
       cnt2.add(lbldesc);
       cnt2.add(cnt1);
       cnt.add(cnt2);
       cnt.add(cnt3);
        return cnt;
    }
}
