/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ilove
 */
public class TaxPayer {

    private int tfn; //TFN number
    private String firstName;//taxpayer first name
    private String lastName;//taxpayer last name
    private String address;//taxpayer address
    private String phone;//phone 

    private int income;
    private int deductible;

    private int taxHeld = 0;
    private int taxReturned = 0;
    //constructor
    public TaxPayer() {

    }
    //constructor
    public TaxPayer(int tfn, String firstName, String lastName, String address, String phone, int income, int deductible, int taxHeld, int taxReturned) {
        this.tfn = tfn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.income = income;
        this.deductible = deductible;
        this.taxHeld = 0;
        this.taxReturned = 0;
    }
    //getset
    public int getTfn() {
        return tfn;
    }

    public void setTfn(int tfn) {
        this.tfn = tfn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getDeductible() {
        return deductible;
    }

    public void setDeductible(int deductible) {
        this.deductible = deductible;
    }

    public int getTaxHeld() {
        return taxHeld;
    }

    public void setTaxHeld(int taxHeld) {
        this.taxHeld = taxHeld;
    }

    public int getTaxReturned() {
        return taxReturned;
    }

    public void setTaxReturned(int taxReturned) {
        this.taxReturned = taxReturned;
    }

    
}
