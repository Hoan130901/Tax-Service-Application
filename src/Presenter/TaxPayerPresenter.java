/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;


/**
 *
 * @author ilove
 */
import View.ITaxPayerView;
import View.TaxPayerView;
import Model.TaxPayer;
import Model.ITaxpayerModel;
import java.util.List;

public class TaxPayerPresenter {
    ITaxPayerView view;
    ITaxpayerModel model;
    List< TaxPayer > results;
    int currentEntryIndex;
    int numberOfEntries;
    TaxPayer currentEntry;
    int taxHeld = 0;
    int taxReturned=0;

    public TaxPayerPresenter(ITaxPayerView view, ITaxpayerModel model) {
        this.view = view;
        this.model = model;
        currentEntryIndex = 0;
        numberOfEntries = 0;
        results = null;
        currentEntry = new TaxPayer();
    }
    
    private void populateAllTextFields() {
        
        view.setTfnText(""+currentEntry.getTfn());
        view.setFrstName(""+currentEntry.getFirstName());
        view.setLstName(""+currentEntry.getLastName());
        view.setAddrssText(""+currentEntry.getAddress());
        view.setPhoneTxtField(""+currentEntry.getPhone());
        view.setIncome(""+currentEntry.getIncome());
        view.setDeductible(""+currentEntry.getDeductible());
        view.setTaxHeld(""+getTaxHeld());
        view.setTaxReturned(""+getTaxReturned());
        view.setIndexTextField(""+(currentEntryIndex+1));
        view.setMaxTextField(""+numberOfEntries);
    }
    
   // handles call when previousButton is clicked
   public void showPrevious() {   
      currentEntryIndex--;
      // wrap around
      if ( currentEntryIndex < 0 )
         currentEntryIndex = numberOfEntries - 1;
      currentEntry = results.get( currentEntryIndex );
      populateAllTextFields();
   }

   // handles call when nextButton is clicked
   public void showNext() {
      currentEntryIndex++;
      // wrap around
      if ( currentEntryIndex >= numberOfEntries )
         currentEntryIndex = 0;
      currentEntry = results.get( currentEntryIndex );
      populateAllTextFields();
   }

   // handles call when queryButton is clicked
   public void performQueryByTFN(int tfn) {
       
    
     results = model.getTaxPayersByTFN(tfn);
     
      numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
         currentEntryIndex = 0;
         currentEntry = results.get( currentEntryIndex );
         populateAllTextFields();
         view.enableNext();
         view.enablePrevious();
         
      } 
      else
        view.showMessageDialog("Tax Payer not found in the System","Error");
   }
    public void performQueryByLastName(String lastName) {
       
     results = model.getTaxPayersByLastName(lastName);
     
      numberOfEntries = results.size();
      if ( numberOfEntries != 0 ) {
         currentEntryIndex = 0;
         currentEntry = results.get( currentEntryIndex );
         populateAllTextFields();
         view.enableNext();
         view.enablePrevious();  
      } 
      else
        view.showMessageDialog("Tax Payer not found in the System","Error");
   }
   // handles call when Browse All Button is clicked
   public void browse() {
      try {
         results = model.getTaxPayers();        
         numberOfEntries = results.size();
         if(numberOfEntries ==0)
             view.showMessageDialog("No Tax Payer in the system","Error");
         if (numberOfEntries != 0 ) {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex );
            populateAllTextFields();
            view.enableNext();
            view.enablePrevious();
         }
      }
      
      catch ( Exception e ) {
         e.printStackTrace();
      }
   }

   // handles call when addButton is clicked
   public void addNewTaxPayer() {
      
      int result = model.addTaxPayer(
              view.getTfnText(),
              view.getfstName(),
              view.getLstName(),
              view.getAddrssText(),
              view.getPhoneTxtField(),
              view.getIncome(), 
              view.getDeductible(), 
              getTaxHeld(),
              getTaxReturned());
      if ( result == 1 )
          view.showMessageDialog("Tax Payer Added","Success");
      else
          
          view.showMessageDialog("Tax Payer not added","Error");
      //browse();
   }
   //handles call when update Button is clicked
   public void updateTaxPayer()
   {
      int result = model.updateTaxPayer(
              view.getTfnText(),
              view.getfstName(),
              view.getLstName(),
              view.getAddrssText(),
              view.getPhoneTxtField(),
              view.getIncome(), 
              view.getDeductible(), 
              getTaxHeld(),
              getTaxReturned());
      if ( result == 1 )
           view.showMessageDialog("Tax Payer Updated","Success");
      else
           view.showMessageDialog("Tax Payer Not Updated","Error");
   }
   //compute TaxHeld
   public int getTaxHeld() {
        taxHeld = computeTax(view.getIncome());
        return taxHeld;
    }
//comput TaxReturned
    public int getTaxReturned() {
        int actualTax = computeTax(view.getIncome() - view.getDeductible());
        taxReturned = (taxHeld - actualTax);

        return taxReturned;
    }
    public void  Exit(){
        System.exit(1);
    }

    //compute tax accoring to different rate, can use other way to calculate it
    public int computeTax(int income1) //income1 as the parameter of annual income
    {
        final double TAX_RATE1 = 0.19;
        final double TAX_RATE2 = 0.325;
        final double TAX_RATE3 = 0.37;
        final double TAX_RATE4 = 0.47;

        final int THR1 = 18200;// Threshold income 1
        final int THR2 = 37000;
        final int THR3 = 87000;
        final int THR4 = 180000;

        int tax = 0;

        if (income1 <= THR1) {
            tax = 0;
        } else if (income1 <= THR2) {
            tax = (int) ((income1 - THR1) * TAX_RATE1);
        } else if (income1 <= THR3) {
            tax = (int) (3572 + (income1 - THR2) * TAX_RATE2);
        } else if (income1 < THR4) {
            tax = (int) (19822 + (income1 - THR3) * TAX_RATE3);
        } else {
            tax = (int) (54232 + (income1 - THR4) * TAX_RATE4);
        }
        return tax;
    }//end of method
   // handles window closure
   public void close() {
      model.close();
   }
    
}
