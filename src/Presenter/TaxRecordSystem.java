/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.ITaxpayerModel;
import Model.TaxpayerModel;
import View.ITaxPayerView;
import View.TaxPayerView;

/**
 *
 * @author ilove
 */
public class TaxRecordSystem {
    
     public static void main(String args[]) {
        TaxPayerView view = new TaxPayerView(); //create new instance for TaxPayerView
        TaxpayerModel model = new TaxpayerModel();//create new instance for TaxPayerModel
        TaxPayerPresenter presenter = new TaxPayerPresenter(view, model);//create new instance for TaxPayerPresenter
        view.bind(presenter);//bind
        view.setVisible(true);//run GUI
        
        
//    java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TaxPayerView().setVisible(true);
//            }
//        });    
    }
}
