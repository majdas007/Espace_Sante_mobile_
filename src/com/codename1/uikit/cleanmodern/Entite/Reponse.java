/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern.Entite;


/**
 *
 * @author majd
 */
public class Reponse {
    
    
    private String id_rep ; 
    private String contenu_rep ;
    private  int nbr_aime_rep  = 0; 
    private String Date_pub ; 
    private String nom , prenom ; 
    private  int id_question ;
    private  static int  id_user = 3;
    static Reponse instance ; 

    public Reponse( String contenu_rep) {
        
        this.contenu_rep = contenu_rep;
    }

    public String getDate_pub() {
        return Date_pub;
    }

    public void setDate_pub(String Date_pub) {
        this.Date_pub = Date_pub;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Reponse (String contenu_rep , int id_question  )
    {
    this.contenu_rep = contenu_rep ;
    this.id_question = id_question;
    
    
    }
    public Reponse ( String id_rep ,String contenu_rep , String Date_pub , int nbr_aime , String nom , String prenom)
    {
        this.contenu_rep = contenu_rep;
        this.Date_pub = Date_pub ; 
        this.nbr_aime_rep = nbr_aime;
        this.nom = nom;
        this.prenom = prenom ;
        this.id_rep= id_rep;
    }
        
    public String getId_rep() {
        return id_rep;
    }

    public void setId_rep(String id_rep) {
        this.id_rep = id_rep;
    }

    public String getContenu_rep() {
        return contenu_rep;
    }

    public void setContenu_rep(String contenu_rep) {
        this.contenu_rep = contenu_rep;
    }

    public int getNbr_aime_rep() {
        return nbr_aime_rep;
    }

    public void setNbr_aime_rep(int nbr_aime_rep) {
        this.nbr_aime_rep = nbr_aime_rep;
    }


    public  int getId_question() {
        return id_question;
    }

    public  void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public static  int getId_user() {
        return id_user;
    }

    public  static void setId_user(int id_user) {
        Reponse.id_user = id_user;
    }
 public Reponse()
 {
     
 }
    public static Reponse getInstance() {
           if(instance == null )
        {
            return instance = new Reponse();
        }
        return instance ;  
    }

    

   
            
    
    
        
        

    
    
    
    
    
}
