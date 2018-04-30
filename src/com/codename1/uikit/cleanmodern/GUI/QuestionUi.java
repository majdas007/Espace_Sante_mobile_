/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.uikit.cleanmodern.Entite.Question;
import com.codename1.uikit.cleanmodern.Entite.Reponse;
import com.codename1.uikit.cleanmodern.ForumBasic;
import com.codename1.uikit.cleanmodern.Service.QuestionService;
import com.codename1.uikit.cleanmodern.Service.ReponseService;
import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codename1.uikit.cleanmodern.ObjetsForm1;
import com.codename1.uikit.cleanmodern.ProfileForm;
import com.codename1.uikit.cleanmodern.WalkthruForm;
import com.codename1.ui.Image;

/**
 *
 * @author majd
 */
public class QuestionUi {
      Form f;
      Button btn;
       private Resources theme;
      Label lb; 
      private ImageViewer img ;


    String id;
    
   
     public QuestionUi( String  id ) {
        img = new ImageViewer();
         ReponseService ReponseService =new ReponseService();

        btn = new Button("back");
        lb = new Label("");
        f = new Form("Questions",BoxLayout.y());
        Button add = new Button("Repondre");
        Dialog dlg = new Dialog("Ajouter Question");
       TextArea edittext = new TextArea(15, 15, 10);
       Button confirm = new Button("ajouter ");
       Button cancel = new Button("annuler");
       dlg.add(edittext);
       dlg.add(confirm);
       add.addActionListener((evt) -> {
           dlg.show();
       });
       confirm.addActionListener((evt) -> {
           ReponseService.addRep(edittext.getText(),id);
       });
     /*   img.setImage(theme.getImage("profile-jpg.jpg"));
        f.add(img);*/
        f.add(add);
        f.add(lb);
          QuestionService question = new QuestionService();
          List <Question> list1 = new ArrayList<>();
          list1= question.SpecQuestion();
         List <Reponse> list = new ArrayList<>();
        list = ReponseService.getrep(id);
        for  ( Question q : list1 )
        {
            try {
                System.out.println(q.getId_question());
            } catch (Exception e) {
                System.out.println(e);
            }
            
            
        }
        for( Reponse e : list)
        {
            f.add(addItem(e));
        }
          
        
        
        
        
        
     
       
        
            
         
        
        
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


    
    
    
    
}
