/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author bounce
 */
public class User {
    String UserId;
    String fname;
    String lname;
    String Username;
    String password;
    String email;
    String DateIncluded;
    String UserType;
    String image;

    public User(String UserId, String fname, String lname, String Username, String password, String email, String DateIncluded, String UserType, String image) {
        this.UserId = UserId;
        this.fname = fname;
        this.lname = lname;
        this.Username = Username;
        this.password = password;
        this.email = email;
        this.DateIncluded = DateIncluded;
        this.UserType = UserType;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateIncluded(String DateIncluded) {
        this.DateIncluded = DateIncluded;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }
    
    

    public String getUserId() {
        return UserId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDateIncluded() {
        return DateIncluded;
    }

    public String getUserType() {
        return UserType;
    }

    public String getImage() {
        return image;
    }
    
    
 public String InserUser(){
     String query="INSERT INTO User(UserId,UserName,Firstname,Lastname,Email,Password,DateIncluded,Usertype,Image)"+
             "VALUES("+"'"+getUserId()+"',"+"'"+getUsername()+"',"+"'"+getFname()+"',"+"'"+getLname()+"',"+"'"+getEmail()+
             "',"+"'"+getPassword()+"',"+"'"+getDateIncluded()+"',"+"'"+getUserType()+"',"+"'"+getImage()+"');";
     return query;
 }   
    
/* public String InsertCustomerDetails(){
       String query="INSERT INTO Customer(LinsenceNumber,FullName,Phonenumber,Email,WorkPlaceAddress,DateOfBirth)"+
               "VALUES("+"'"+getLicenceNumber()+"',"+"'"+getFullname()+"',"+"'"+getPhonenumber()+
               "',"+"'"+getEmail()+"',"+"'"+getWorkAddress()+"',"+"'"+getDateOfbirth()+"');";
        return query;
}   
    */

}
