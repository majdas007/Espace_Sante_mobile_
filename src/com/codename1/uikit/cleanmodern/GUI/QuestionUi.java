/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.Entite.Question;
import com.codename1.uikit.cleanmodern.Service.QuestionService;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author majd
 */
public class QuestionUi {
      Form f;
    Label lb;
    String id;
    
     public QuestionUi() {
        Resources res = null;
        f = new Form("Questions");
        
        
        QuestionService QuestionService =new QuestionService();
        List <Question> list = new ArrayList<>();
        list = QuestionService.SpecQuestion();
       
         
            lb = new Label();
            lb.setText(QuestionService.SpecQuestion().toString());

f.add(lb);
        
         
         
         
          
    }
     
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    private void addButton(Image img, String title, boolean liked, String likeCount, String commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Reponses", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       
      
   }
    
    
}
