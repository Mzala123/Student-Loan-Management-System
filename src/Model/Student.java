/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author bounce
 */
public class Student {
    String studentId;
    String StudentName;
    String Email;
    String Phonenumber;
    String YearOfStudy;
    String EnrollmentYear;
    String Duration;
    String Programme;
    String Gender;
    JFXButton Update;
    JFXButton Archive;
    JFXButton Apply_loan;
    JFXButton Confirm_loan;
    String image;

    public Student(String studentId, String StudentName, String Email, String Phonenumber, String YearOfStudy, String EnrollmentYear, String Duration, String Programme, JFXButton Update, JFXButton Archive, JFXButton Apply_loan, JFXButton Confirm_loan, String image, String Gender) {
        this.studentId = studentId;
        this.StudentName = StudentName;
        this.Email = Email;
        this.Phonenumber = Phonenumber;
        this.YearOfStudy = YearOfStudy;
        this.EnrollmentYear = EnrollmentYear;
        this.Duration = Duration;
        this.Programme = Programme;
        this.Update = Update;
        this.Archive = Archive;
        this.Apply_loan = Apply_loan;
        this.Confirm_loan = Confirm_loan;
        this.image=image;
        this.Gender = Gender;
    }

    public Student() {
    }

    public Student(String studentId, String StudentName, String Email, String Phonenumber, String YearOfStudy, String EnrollmentYear, String Duration, String Programme, String image, String Gender) {
        this.studentId = studentId;
        this.StudentName = StudentName;
        this.Email = Email;
        this.Phonenumber = Phonenumber;
        this.YearOfStudy = YearOfStudy;
        this.EnrollmentYear = EnrollmentYear;
        this.Duration = Duration;
        this.Programme = Programme;
        this.image=image;
        this.Gender = Gender;
        
    }

    public Student(String studentId, String StudentName, String YearOfStudy, String Duration) {
        this.studentId = studentId;
        this.StudentName = StudentName;
        this.YearOfStudy = YearOfStudy;
        this.Duration = Duration;
    }

    public Student(String studentId, String StudentName,String Gender,String YearOfStudy, String Programme, JFXButton Update, JFXButton Archive, JFXButton Apply_loan ) {
        this.studentId = studentId;
        this.StudentName = StudentName;
        this.Gender=Gender;
        this.YearOfStudy = YearOfStudy; 
        this.Programme = Programme;
        this.Update = Update;
        this.Archive = Archive;
        this.Apply_loan = Apply_loan;
        
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPhonenumber(String Phonenumber) {
        this.Phonenumber = Phonenumber;
    }

    public void setYearOfStudy(String YearOfStudy) {
        this.YearOfStudy = YearOfStudy;
    }

    public void setEnrollmentYear(String EnrollmentYear) {
        this.EnrollmentYear = EnrollmentYear;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public void setProgramme(String Programme) {
        this.Programme = Programme;
    }

    public void setUpdate(JFXButton Update) {
        this.Update = Update;
    }

    public void setArchive(JFXButton Archive) {
        this.Archive = Archive;
    }

    public void setApply_loan(JFXButton Apply_loan) {
        this.Apply_loan = Apply_loan;
    }

    public void setConfirm_loan(JFXButton Confirm_loan) {
        this.Confirm_loan = Confirm_loan;
    }
    
    

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public String getYearOfStudy() {
        return YearOfStudy;
    }

    public String getEnrollmentYear() {
        return EnrollmentYear;
    }

    public String getDuration() {
        return Duration;
    }

    public String getProgramme() {
        return Programme;
    }

    public JFXButton getUpdate() {
        return Update;
    }

    public JFXButton getArchive() {
        return Archive;
    }

    public JFXButton getApply_loan() {
        return Apply_loan;
    }

    public JFXButton getConfirm_loan() {
        return Confirm_loan;
    }

    public String getImage() {
        return image;
    }

    public String getGender() {
        return Gender;
    }
    
    
//     public String InserUser(){
//     String query="INSERT INTO User(UserId,UserName,Firstname,Lastname,Email,Password,DateIncluded,Usertype,Image)"+
//             "VALUES("+"'"+getUserId()+"',"+"'"+getUsername()+"',"+"'"+getFname()+"',"+"'"+getLname()+"',"+"'"+getEmail()+
//             "',"+"'"+getPassword()+"',"+"'"+getDateIncluded()+"',"+"'"+getUserType()+"',"+"'"+getImage()+"');";
//     return query;
// }   
    
    public String RegisterStudent()
    {
         String query="INSERT INTO Student(StudentId,StudentName,Email,Phonenumber,EnrollmentYear,Gender,YearOfStudy,Duration,Programme,Image)"+
             "VALUES("+"'"+getStudentId()+"',"+"'"+getStudentName()+"',"+"'"+getEmail()+"',"+"'"+getPhonenumber()+"',"+"'"+getEnrollmentYear()+
             "',"+"'"+getGender()+"',"+"'"+getYearOfStudy()+"',"+"'"+getDuration()+"',"+"'"+getProgramme()+"',"+"'"+getImage()+"');";
     return query;
     
    }
    
    public String UpdateStudentRecord()
    {
//          String query1="UPDATE Customer SET FullName='"+getFullname()+"',Phonenumber='"+getPhonenumber()+"',Email='"+getEmail()+"',"
//               +"WorkPlaceAddress='"+getWorkAddress()+"',DateOfBirth='"+getDateOfbirth()+"'WHERE LinsenceNumber='"+getLicenceNumber()+"'";
     
         String query = "UPDATE Student SET StudentName='"+getStudentName()+"',Email='"+getPhonenumber()+"',Phonenumber='"+getEmail()+"',"
               +"Gender='"+getGender()+"',EnrollmentYear='"+getEnrollmentYear()+"',Duration='"+getDuration()+"',Programme='"+getProgramme()+"',"
                 + "YearOfStudy='"+getYearOfStudy()+"',Image='"+getImage()+"'WHERE StudentId='"+getStudentId()+"'";
       return query;
    }
    
     public String DeleteStudentDetails(String id)
   {
       
       String query="DELETE FROM Student WHERE StudentId='"+ id +"'";
       return query;
   }
}
