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
public class User {
    
    
    
     private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String adress;
    private String telephone;
   static private String status ="active";
    private int solde;
    private String avatar;
   
    private String login;
    private String username_canonical;
    private String email_canonical;
    private int enabled;



    public User() {
    }

    public User(String username, String password, String email, String firstname, String lastname, String adress, String telephone, String status,int solde,String avatar
    ,String login,String username_canonical, String email_canonical, int enabled )
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.telephone = telephone;
        this.solde=solde;
        this.avatar=avatar;
        this.login=login;
        this.username_canonical=username_canonical;
        this.email_canonical=email_canonical;
        this.enabled=enabled;
    }

    public User(String username, String password, String email, String firstname, String lastname, String adress, String telephone, String status, String avatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.telephone = telephone;
        this.status = status;
        this.avatar = avatar;
    }

    public User(Integer id, String username, String password, String email, String firstname, String lastname, String adress, String telephone,String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.telephone = telephone;
        
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", adress=" + adress + ", telephone=" + telephone + ", status=" + status + ", solde=" + solde + ", avatar=" + avatar + ", login=" + login + ", username_canonical=" + username_canonical + ", email_canonical=" + email_canonical + ", enabled=" + enabled + '}';
    }



    

 
    
}