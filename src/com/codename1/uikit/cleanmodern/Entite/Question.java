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
public class Question {
    
    private String id_question ; 
   
    private String contenu_question ;
    private String nom_catF ; 
    private  String id_user ;
    private  String nbr_rep ;
    String nom , prenom,avatar ;
    
    public String getDate_publication() {
        return Date_publication;
    }
    

    public void setDate_publication(String Date_publication) {
        this.Date_publication = Date_publication;
    }
    String Date_publication ;
    private String sujet_question ;
    private boolean approved_question = false ; 
    static Question instance ; 

    public Question( String id_user ,String id_ques,String nom , String prenom , String contenu_question , String sujet , boolean approved )
    
    {
    this.id_user = id_user;
    this.nom = nom ; 
    this.prenom = prenom; 
    this.id_question = id_ques;
    this.contenu_question = contenu_question;
    this.sujet_question = sujet ;
    this.approved_question = approved;
    
    
    }
    
    
    
    
     public Question( String sujet_question  ,String contenu_question , String nom_catF ) {
        
        this.sujet_question = sujet_question;
        this.contenu_question = contenu_question;
        this.nom_catF = nom_catF;
        
    }
    
  
     public Question (String id_question, String contenu_question , String sujet_question ,  String Date_publication , String nbr_rep , String nom , String prenom)
     {
         this.contenu_question = contenu_question ; 
         this.sujet_question = sujet_question ; 
         this.Date_publication = Date_publication ;
         this.nom = nom ;
         this.prenom = prenom ;
         this.nbr_rep = nbr_rep ; 
        this.id_question= id_question;
       
     }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
     
      public Question (String id_question, String contenu_question , String sujet_question ,  String Date_publication , String nbr_rep , String nom , String prenom,String avatar )
     {
         this.contenu_question = contenu_question ;
         this.sujet_question = sujet_question ; 
         this.Date_publication = Date_publication ;
         this.nom = nom ;
         this.prenom = prenom ;
         this.nbr_rep = nbr_rep ; 
        this.id_question= id_question;
        this.avatar = avatar;
     }
     
     
      public Question (String contenu_question , String sujet_question ,  String Date_publication , String nbr_rep , String nom , String prenom )
     {
         this.contenu_question = contenu_question ; 
         this.sujet_question = sujet_question ; 
         this.Date_publication = Date_publication ;
         this.nom = nom ;
         this.prenom = prenom ;
         this.nbr_rep = nbr_rep ; 
        
     }
      public Question ( String contenu_question , String sujet_question ,  String Date_publication , String nbr_rep , String id_user )
     {
         this.contenu_question = contenu_question ; 
         this.sujet_question = sujet_question ; 
         this.Date_publication = Date_publication ;
         this.id_question = id_question;
         this.nbr_rep = nbr_rep ; 
         this.id_user = id_user ; 
     }
    public Question() {
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

    public  String getNbr_rep() {
        return nbr_rep;
    }

    public  void setNbr_rep(String nbr_rep) {
        this.nbr_rep = nbr_rep;
    }
    

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }

    public String getContenu_question() {
        return contenu_question;
    }

    public void setContenu_question(String contenu_question) {
        this.contenu_question = contenu_question;
    }

    public String getNom_catF() {
        return nom_catF;
    }

    public void setNom_catF(String nom_catF) {
        this.nom_catF = nom_catF;
    }

   
    public String getId_user() {
        return id_user;
    }

    public  void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getSujet_question() {
        return sujet_question;
    }

    public void setSujet_question(String sujet_question) {
        this.sujet_question = sujet_question;
    }

    public boolean isApproved_question() {
        return approved_question;
    }

    public void setApproved_question(boolean approved_question) {
        this.approved_question = approved_question;
    }

    @Override
    public String toString() {
        return "Question{" + "id_question=" + id_question + ", contenu_question=" + contenu_question + ", nom_catF=" + nom_catF + ", id_user=" + id_user + ", nbr_rep=" + nbr_rep + ", nom=" + nom + ", prenom=" + prenom + ", avatar=" + avatar + ", Date_publication=" + Date_publication + ", sujet_question=" + sujet_question + ", approved_question=" + approved_question + '}';
    }

    
   public String ListQuestion()  
   {
        return sujet_question ;
   }
       
}  
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 

   
    
    
    
    

