/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.TaxPayer;
import Presenter.TaxPayerPresenter;

/**
 *
 * @author ilove
 */
public interface ITaxPayerView {
   void setTfnText(String tfn);
    int getTfnText();
    void setFrstName(String fName);
    String getfstName(); 
    void setLstName(String lName );
    String getLstName();
    void setAddrssText(String s);
    String getAddrssText();
    void setPhoneTxtField(String s);
    String getPhoneTxtField(); 
    void setMaxTextField(String s);
    String getMaxTextField(); 
    void setIndexTextField(String s);
    String getIndexTextField(); 
    void setQueryTFN(String s);
    int getQueryTFN();
    void setQueryLName(String s);
    String getQueryLName();
    void setIncome(String income);
    int getIncome();
    void setDeductible(String deductible);
    int getDeductible();
    void setTaxHeld(String taxHeld);
    int getTaxHeld();
    void setTaxReturned(String taxReturned);
    int getTaxReturned();
    
    void enableNext();
    void enablePrevious();
    
    void showMessageDialog( String s1, String s2 );
   
}
