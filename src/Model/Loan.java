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
public class Loan{
  String Studentname;
  String StudentId;  
  String loanType;
  Double LoanAmount;
  String LoanReason;
  String Status;
  JFXButton ConfirmLoan;
  JFXButton IncrementLoan;
  JFXButton ReturnLoan;

    public Loan(String Studentname, String StudentId, String loanType, Double LoanAmount, String LoanReason, String Status, JFXButton ConfirmLoan, JFXButton IncrementLoan, JFXButton ReturnLoan) {
        this.Studentname = Studentname;
        this.StudentId = StudentId;
        this.loanType = loanType;
        this.LoanAmount = LoanAmount;
        this.LoanReason = LoanReason;
        this.Status = Status;
        this.ConfirmLoan = ConfirmLoan;
        this.IncrementLoan = IncrementLoan;
        this.ReturnLoan = ReturnLoan;
    }

    public Loan() {
    }

    public Loan(String Studentname, String StudentId, String loanType, Double LoanAmount, String LoanReason, JFXButton ConfirmLoan) {
        this.Studentname = Studentname;
        this.StudentId = StudentId;
        this.loanType = loanType;
        this.LoanAmount = LoanAmount;
        this.LoanReason = LoanReason;
        this.ConfirmLoan = ConfirmLoan;
    }

    public Loan(String Studentname, String StudentId, String loanType, Double LoanAmount, String LoanReason, String Status, JFXButton IncrementLoan, JFXButton ReturnLoan) {
        this.Studentname = Studentname;
        this.StudentId = StudentId;
        this.loanType = loanType;
        this.LoanAmount = LoanAmount;
        this.LoanReason = LoanReason;
        this.Status = Status;
        this.IncrementLoan = IncrementLoan;
        this.ReturnLoan = ReturnLoan;
    }

    public Loan(String Studentname, String StudentId, String loanType, String LoanReason) {
        this.Studentname = Studentname;
        this.StudentId = StudentId;
        this.loanType = loanType;
        this.LoanReason = LoanReason;
    }
    
    

    public void setStudentname(String Studentname) {
        this.Studentname = Studentname;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setLoanAmount(Double LoanAmount) {
        this.LoanAmount = LoanAmount;
    }

    public void setLoanReason(String LoanReason) {
        this.LoanReason = LoanReason;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setConfirmLoan(JFXButton ConfirmLoan) {
        this.ConfirmLoan = ConfirmLoan;
    }

    public void setIncrementLoan(JFXButton IncrementLoan) {
        this.IncrementLoan = IncrementLoan;
    }

    public void setReturnLoan(JFXButton ReturnLoan) {
        this.ReturnLoan = ReturnLoan;
    }

    public String getStudentname() {
        return Studentname;
    }

    public String getStudentId() {
        return StudentId;
    }

    public String getLoanType() {
        return loanType;
    }

    public Double getLoanAmount() {
        return LoanAmount;
    }

    public String getLoanReason() {
        return LoanReason;
    }

    public String getStatus() {
        return Status;
    }

    public JFXButton getConfirmLoan() {
        return ConfirmLoan;
    }

    public JFXButton getIncrementLoan() {
        return IncrementLoan;
    }

    public JFXButton getReturnLoan() {
        return ReturnLoan;
    }
  
  
}
