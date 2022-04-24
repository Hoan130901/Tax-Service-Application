/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author ilove
 */
public interface ITaxpayerModel {
    public List<TaxPayer> getTaxPayers();
    public List<TaxPayer> getTaxPayersByLastName(String lastName);
    public List<TaxPayer> getTaxPayersByTFN(int tfn );
    public int addTaxPayer(int tfn, String firstName, String lastName, String address, String phone, int income, int deductible, int taxHeld, int taxReturned);
    public int updateTaxPayer(int tfn, String firstName, String lastName, String address, String phone, int income, int deductible,  int taxHeld, int taxReturned);
    public void close();
}
