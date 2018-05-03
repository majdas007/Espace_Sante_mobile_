/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.cleanmodern.Entite.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author majd
 */
public class QuestionService  {
    
    
      public void addQuest(Question q) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/EspaceSante/web/app_dev.php/questionaddapi?contenu=" + q.getContenu_question() + "&subject=" + q.getSujet_question()+"&categorie="+q.getNom_catF();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
            public void editQuest(String id , String contenu) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/EspaceSante/web/app_dev.php/editquestioapi?id=" + id + "&contenu=" + contenu;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
            
           public void deleteQuest(String id ) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/EspaceSante/web/app_dev.php/deletequestioapi?id="+id ;
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }       
            
    
    
    
    
    
    
    
    
  public ArrayList<Question> getListTask(String json) {

        ArrayList<Question> QuestionList = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Question = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Question);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Question.get("root");

            for (Map<String, Object> obj : list) {
                Question e = new Question();
                
                    e.setContenu_question(obj.get("contenuQuest").toString());
                   e.setSujet_question(obj.get("sujetQuestion").toString());
                   e.setDate_publication(obj.get("datePublication").toString());
                   e.setNbr_rep(obj.get("nbrRep").toString());
                   e.setId_question(obj.get("idQuestion").toString());
                   
                
           
                QuestionList.add(e);

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println(QuestionList);
        return QuestionList;

    }
  
  public ArrayList<Question> getspecquest(String json) {

        ArrayList<Question> listq = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Question = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Question);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Question.get("root");

            for (Map<String, Object> obj : list) {
                Question e = new Question();
                
                    e.setContenu_question(obj.get("contenuQuest").toString());
                   e.setSujet_question(obj.get("sujetQuestion").toString());
                   e.setDate_publication(obj.get("datePublication").toString());
                   e.setNbr_rep(obj.get("nbrRep").toString());
                   e.setId_question(obj.get("idQuestion").toString());
                   
                listq.add(e);

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println(listq);
        return listq;

    }
  
           
      
  
 
  
 
  
  
  ArrayList<Question> listQuestion = new ArrayList<>();
  
   public ArrayList<Question> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/apiquest");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                QuestionService ser = new QuestionService();
                listQuestion = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listQuestion;
    }
     ArrayList<Question> listcat = new ArrayList<>();
  
   public ArrayList<Question> getcat( String cat){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/getcat?cat="+cat);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                QuestionService ser = new QuestionService();
                listcat = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcat;
    }
   
   
   
   
   ArrayList<Question> listSpecificQuestion = new ArrayList<>();
  
   public ArrayList<Question> SpecQuestion(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/getquestid?id=4");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                QuestionService ser = new QuestionService();
                listSpecificQuestion = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSpecificQuestion;
    }
   
   
   
   
   
   
   
   
   
    
}
