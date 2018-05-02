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
public class QuestionUi extends BaseForm{
      Form f;
      Button btn;
       private Resources theme;
      Label lb; 
      private ImageViewer img ;


    
   
     public QuestionUi( String  id ) {
        img = new ImageViewer();
         ReponseService ReponseService =new ReponseService();

        btn = new Button("back");
        lb = new Label("");
        f = new Form("Reponse",BoxLayout.y());
        Button add = new Button("Repondre");
        Dialog dlg = new Dialog("Ajouter Reponse");
       TextArea addrep = new TextArea(15, 15, 10);

       Button confirm = new Button("ajouter ");
       Button cancel = new Button("annuler");
       dlg.add(addrep);
       dlg.add(confirm);
       dlg.add(cancel);
       cancel.addActionListener((evt) -> {
           this.getF().show();
       });
       add.addActionListener((evt) -> {
           dlg.show();
       });
       
       confirm.addActionListener((evt) -> {
           ReponseService.addRep(addrep.getText(),id);
           new QuestionUi(id).getF().show();
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
            f.add(addItem(e,id));
        }
           
     
    }
     
     public Container addItem (Reponse r,String id )            
    { 
        
       Container cnt=new Container(BoxLayout.y());
       Dialog dlg1 = new Dialog("Modifier Reponse");
       TextArea editreponse = new TextArea(10, 15, 10);
        Button modifier = new Button("Modifier");
        Button annuler = new Button("annuler");
        dlg1.add(editreponse);    
        dlg1.add(modifier);
        dlg1.add(annuler);
      
        
        
        modifier.addActionListener((evt) -> {
            ReponseService rep = new ReponseService();
            
            if(editreponse.getText().equals(""))
            {
            Dialog.show("error","Champs Vides ","ok", null);
            }
            else {
                rep.editrep(r.getId_rep(), editreponse.getText());
                new QuestionUi(id).getF().show();
            }
            
            
            
        });
        editreponse.setText(r.getContenu_rep());
        annuler.addActionListener((evt) -> {
            this.getF().show();
        });
        
        Container cnt1=new Container(BoxLayout.y());
        Container cnt2=new Container(BoxLayout.x());
        Container cnt3= new Container(BoxLayout.x());
        Button edit = new Button ("Edit");
        Button delete = new Button ("delete");
        
        delete.addActionListener((evt) -> {
     ReponseService rep = new ReponseService();
     rep.deleterep(r.getId_rep());

        });
       
       cnt3.add(edit);
       cnt3.add(delete);
       edit.addActionListener((evt) -> {
           dlg1.show();
       });


        SpanLabel lblid = new SpanLabel(r.getContenu_rep());
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



    
    
    
    
}
