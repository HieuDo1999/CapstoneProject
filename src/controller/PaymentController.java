package controller;

import entity.bike.Bike;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HieuDo
 * @version 2
 */
public class PaymentController {
    private Bike bike;
    public void changeStateBike(Integer id) throws SQLException {
        bike= new Bike();
        bike.changeStateBike(id);
    }

    /**
     *
     * @param cardOwner
     * @return true if Owner is valid
     */
    public boolean validateCardOwner( String cardOwner){
        Pattern p = Pattern.compile("[^0-9a-z_A-Z, ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cardOwner);
        boolean b = m.find();
        if(b) return false;
        if(cardOwner=="")return false;
        return true;
    }

    /**
     *
     * @param cardNumber
     * @return
     */
    public boolean validateCardCode( String cardNumber){
        Pattern p = Pattern.compile("[^0-9a-z_, ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cardNumber);
        boolean b = m.find();
        if(b) return false;
        if(cardNumber=="")return false;
        return true;
    }

    /**
     *
     * @param cvvCode
     * @return
     */
    public boolean validateCVVCode( String cvvCode){
        Pattern p = Pattern.compile("[^0-9, ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cvvCode);
        boolean b = m.find();
        if(b) return false;
        if(cvvCode=="")return false;
        return true;
    }

    /**
     *
     * @param dateExpired
     * @return
     */
    public boolean validatedateExpired( String dateExpired){
        Pattern p = Pattern.compile("[^0-9, ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(dateExpired);
        boolean b = m.find();
        if(b) return false;
        if(dateExpired=="")return false;
        return true;
    }







}
