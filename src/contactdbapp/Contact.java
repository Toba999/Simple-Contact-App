/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactdbapp;


public class Contact {
    int id;
    String name;
    String phone;
    String email;
    
    public Contact(){
        this.id=0;
        this.name="";
        this.phone="";
        this.email="";
    }
    
    public Contact(int id,String name,String phone,String email){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.email=email;
    }
    public Contact(String name,String phone,String email){
        this.name=name;
        this.phone=phone;
        this.email=email;
    }

    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
