/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ilove
 */
public class TaxpayerModel implements ITaxpayerModel {

    private static final String URL = "jdbc:mysql://localhost/taxdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hoanduong05"; //your own password to Root account of MySQL

    private Connection connection = null; // manages connection
    private PreparedStatement selectAllTaxPayers = null;
    private PreparedStatement selectByLastName = null;
    private PreparedStatement selectByTFN = null;
    private PreparedStatement addTaxPayer = null;
    private PreparedStatement updateTaxPayerInfo = null;

    public TaxpayerModel() {
        try {
            connection
                    = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // create query that selects all entries from taxpayers
            selectAllTaxPayers
                    = connection.prepareStatement("SELECT * FROM taxpayers");

            // create query that selects entries with a specific last name
            selectByLastName = connection.prepareStatement(
                    "SELECT * FROM taxpayers WHERE LASTNAME = ?");
            // create query that selects TaxPayer with specific TFN
            selectByTFN = connection.prepareStatement(
                    "SELECT * FROM taxpayers WHERE TFN = ?");
            // create insert that adds a new entry into the taxpayers
            addTaxPayer = connection.prepareStatement(
                    "INSERT INTO taxpayers "
                    + "( TFN, FIRSTNAME, LASTNAME, ADDRESS, PHONE, INCOME, DEDUCTIBLE, TAXHELD, TAXRETURNED ) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //query to update Taxpayer info 
            updateTaxPayerInfo = connection.prepareStatement("UPDATE taxpayers SET TFN = ?, FIRSTNAME = ?, "
                                                                                + "LASTNAME = ?, "
                                                                                + "ADDRESS = ?, "
                                                                                + "PHONE = ?, "
                                                                                + "INCOME = ?, "
                                                                                + "DEDUCTIBLE = ?, "
                                                                                + "TAXHELD = ?,"
                                                                                + "TAXRETURNED = ? WHERE TFN = ?");
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
    }

    @Override
    public List<TaxPayer> getTaxPayers() {// to browse all TaxPAyer
        List< TaxPayer> results = null;
        ResultSet resultSet = null;
        try {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllTaxPayers.executeQuery();
            results = new ArrayList< TaxPayer>();

            while (resultSet.next()) {
                results.add(new TaxPayer(
                        resultSet.getInt("TFN"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("PHONE"),
                        resultSet.getInt("INCOME"),
                        resultSet.getInt("DEDUCTIBLE"),
                        resultSet.getInt("TAXHELD"),
                        resultSet.getInt("TAXRETURNED")
                ));
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    } // end method getAllTaxPayers

    @Override
    public List<TaxPayer> getTaxPayersByLastName(String lastName) {//to Search TaxPayer by LastName
        List<TaxPayer> results = null;
        ResultSet resultSet = null;

        try {
            selectByLastName.setString(1, lastName); // specify last name

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectByLastName.executeQuery();

            results = new ArrayList< TaxPayer>();

            while (resultSet.next()) {
                results.add(new TaxPayer(
                        resultSet.getInt("TFN"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("PHONE"),
                        resultSet.getInt("INCOME"),
                        resultSet.getInt("DEDUCTIBLE"),
                        resultSet.getInt("TAXHELD"),
                        resultSet.getInt("TAXRETURNED")
                ));
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    }

    @Override
    public List<TaxPayer> getTaxPayersByTFN(int tfn) {//To search TaxPayer by TFN
        List< TaxPayer> results = null;
        ResultSet resultSet = null;

        try {
            selectByTFN.setInt(1, tfn); // specify id

            // executeQuery returns ResultSet containing matching entries
            resultSet = selectByTFN.executeQuery();

            results = new ArrayList< TaxPayer>();

            while (resultSet.next()) {
                results.add(new TaxPayer(
                        resultSet.getInt("TFN"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("PHONE"),
                        resultSet.getInt("INCOME"),
                        resultSet.getInt("DEDUCTIBLE"),
                        resultSet.getInt("TAXHELD"),
                        resultSet.getInt("TAXRETURNED")
                ));
            } // end while
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally {
            try {
                resultSet.close();
            } // end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            } // end catch
        } // end finally

        return results;
    }

    @Override
    //add TaxPayer
    public int addTaxPayer(int tfn, String firstName, String lastName, String address, String phone, int income, int deductible, int taxHeld, int taxReturned) {
        int result = 0;

        // set parameters, then execute insertNewPatient
        try {
            addTaxPayer.setInt(1, tfn);
            addTaxPayer.setString(2, firstName);
            addTaxPayer.setString(3, lastName);
            addTaxPayer.setString(4, address);
            addTaxPayer.setString(5, phone);
            addTaxPayer.setInt(6, income);
            addTaxPayer.setInt(7, deductible);
            addTaxPayer.setInt(8, taxHeld);
            addTaxPayer.setInt(9, taxReturned);
            // insert the new entry; returns # of rows updated
            result = addTaxPayer.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    }

    
    public int updateTaxPayer(int tfn, String firstName, String lastName, String address, String phone, int income, int deductible,  int taxHeld, int taxReturned) {
         int result = 0;

        // set parameters, then execute insertNewPatient
        try {
            updateTaxPayerInfo.setInt(1, tfn);
            updateTaxPayerInfo.setString(2, firstName);
            updateTaxPayerInfo.setString(3, lastName);
            updateTaxPayerInfo.setString(4, address);
            updateTaxPayerInfo.setString(5, phone);
            updateTaxPayerInfo.setInt(6, income);
            updateTaxPayerInfo.setInt(7, deductible);
            updateTaxPayerInfo.setInt(8, taxHeld);
            updateTaxPayerInfo.setInt(9, taxReturned);
            updateTaxPayerInfo.setInt(10, tfn);

            // insert the new entry; returns # of rows updated
            result = updateTaxPayerInfo.executeUpdate();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } // end catch

        return result;
    
    }


}
