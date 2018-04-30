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
import com.codename1.uikit.cleanmodern.Entite.Reponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author majd
 */
public class ReponseService {
    
    
     public void addRep(String q,String id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/EspaceSante/web/app_dev.php/addrepapi?addRep=" + q+ "&id=" + id;
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
            
    
    
    
    
    
    
    
    
    
    
    
    
    public Reponse Signlerep( String json )
    {
        Reponse rep = new Reponse();
        try {
            
             JSONParser j = new JSONParser();
             Map<String, Object> reponse = j.parseJSON(new CharArrayReader(json.toCharArray()));
             
             List<Map<String, Object>> list = (List<Map<String, Object>>) reponse.get("root");
             for (Map<String, Object> obj : list) {
             
             rep.setContenu_rep(obj.get("contenuRep").toString());
             
             
             }

            
            
        } catch (Exception e) {
        }
        
        return rep;
    }
    public ArrayList<Reponse> getListTask(String json) {

        ArrayList<Reponse> ReponseList = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Reponse = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Reponse);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Reponse.get("root");

            for (Map<String, Object> obj : list) {
                Reponse e = new Reponse();
                e.setContenu_rep(obj.get("contenuRep").toString());
                e.setId_rep(obj.get("idRep").toString());
                  
             
                ReponseList.add(e);

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println(ReponseList);
        return ReponseList;

    }
    
    
  ArrayList<Reponse> listreponse= new ArrayList<>();
      
       public ArrayList<Reponse>  getrep(String s){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/getrepapi?id="+s);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReponseService ser = new ReponseService();
               listreponse  = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listreponse;
    }
      
      

    
}
