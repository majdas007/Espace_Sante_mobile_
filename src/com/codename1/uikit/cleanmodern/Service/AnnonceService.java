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
import com.mycompany.myapp.Entite.Annonce;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tarek
 */
public class AnnonceService {

//    public ArrayList<Annonce> getListTask(String json) {
//
//        ArrayList<Annonce> AnnonceList = new ArrayList<>();
//
//        try {
//            System.out.println(json);
//            JSONParser j = new JSONParser();
//
//            Map<String, Object> Annonce = j.parseJSON(new CharArrayReader(json.toCharArray()));
//            System.out.println(Annonce);
//
//            List<Map<String, Object>> list = (List<Map<String, Object>>) Annonce.get("root");
//
//            for (Map<String, Object> obj : list) {
//            Annonce e = new Annonce();
//                e.setTitre_annonce(obj.get("titreAnnonce").toString());
////                e.setDate_annonce(obj.get("dateAnnonce").toString());
////                e.setAddr_annonce(obj.get("addrAnnonce").toString());
////                e.setDesc_annonce(obj.get("descAnnonce").toString());
//                // e.setTel_annonce(Integer.valueOf.get("dateAnnonce").toString());
//                //e.setImg_annonce(obj.get("imgAnnonce").toString());
//                AnnonceList.add(e);
//
//            }
//
//        } catch (IOException ex) {
//        }
//        System.out.println(AnnonceList);
//        return AnnonceList;
//
//    }
    public ArrayList<Annonce> getListTask(String json) {

        ArrayList<Annonce> AnnonceList = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Annonce = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Annonce);

            List<Map<String, Object>> list = (List<Map<String, Object>>) Annonce.get("root");

                for (Map<String, Object> obj : list) {
                Annonce a = new Annonce();
                //    AnnonceList.add(obj.get("titreAnnonce").toString());

                a.setId_annonce(obj.get("idAnnonce").toString());
                a.setTitre_annonce(obj.get("titreAnnonce").toString());
                a.setAddr_annonce(obj.get("addrAnnonce").toString());
                a.setDesc_annonce(obj.get("descAnnonce").toString());
                //a.setTel_annonce(obj.get("telAnnonce").toString());
                a.setImg_annonce(obj.get("NomImage").toString());
                

                AnnonceList.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(AnnonceList);
        return AnnonceList;

    }

    ArrayList<Annonce> listAnnonce = new ArrayList<>();

    public void addEvent(Annonce q) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/EspaceSante/web/app_dev.php/AjoutEventMobile?" + "titre=" + q.getTitre_annonce() + "&description=" + q.getDesc_annonce() + "&adresse=" + q.getAddr_annonce() + "&tel=" + q.getTel_annonce();
        con.setUrl(Url);

        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void addOffre(Annonce q) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/EspaceSante/web/app_dev.php/AjoutOffreMobile?" + "titre=" + q.getTitre_annonce() + "&description=" + q.getDesc_annonce() + "&adresse=" + q.getAddr_annonce() + "&tel=" + q.getTel_annonce();
        con.setUrl(Url);

        //System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public ArrayList<Annonce> List2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/AfficherEventMobile/Evenement");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                listAnnonce = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }

    public ArrayList<Annonce> testlist() {
        ConnectionRequest con = new ConnectionRequest();
                con.setUrl("http://localhost/EspaceSante/web/app_dev.php/AfficherOffreMobile/Offre%20d%27emploi");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                listAnnonce = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }

//    public void deleteOffre(String id) {
//        ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost/EspaceSante/web/app_dev.php/DeleteOffreMobile?id=" + id;
//        con.setUrl(Url);
//
//        //System.out.println("tt");
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//    }

    ArrayList<Annonce> listoffre = new ArrayList<>();

    public ArrayList<Annonce> getListDet(String id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/afficherOffreMobileDetail/"+ id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                listoffre = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listoffre;
    }

    public ArrayList<Annonce> getListDetE(String id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspaceSante/web/app_dev.php/afficherEventMobileDetail/"+ id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                listoffre = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listoffre;
    }

 
     public void editOffre(String id  ,String titre,String desc,String adr,String img) {
        ConnectionRequest con = new ConnectionRequest();
            String Url = ("http://localhost/EspaceSante/web/app_dev.php/modifierOffreMobile?id="+id+"&titre="+titre+"&description="+desc+"&adresse="+adr+"&image="+img );
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
      public void editEvent(String id  ,String titre,String desc,String adr,String img) {
        ConnectionRequest con = new ConnectionRequest();
            String Url = ("http://localhost/EspaceSante/web/app_dev.php/modifierEventMobile?id="+id+"&titre="+titre+"&description="+desc+"&adresse="+adr+"&image="+img );
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
      public void deleterep(String id ) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = ("http://localhost/EspaceSante/web/app_dev.php/DeleteOffreMobile?id="+id );
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   public void deleterepE(String id ) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = ("http://localhost/EspaceSante/web/app_dev.php/DeleteEventMobile?id="+id );
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}