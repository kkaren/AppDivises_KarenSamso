package edu.ub.pis2015.karensamso.appdivises_karensamso;

import android.app.Activity;
import android.os.Bundle;
import java.io.Serializable;

/**
 * Created by Karen on 03/06/2015.
 */
public class PreviousConversion implements Serializable {

    private float value;
    private String fromCoin;
    private double rate;
    private String toCoin;
    private float comission;
    private String result;

    public PreviousConversion( float value, String fromCoin, double rate, String toCoin, float comission, String result){
            this.value = value;
            this.fromCoin = fromCoin;
            this.rate = rate;
            this.toCoin = toCoin;
            this.comission = comission;
            this.result = result;
    }

    public String toString(){
        String conversion;
        conversion = value+" "+fromCoin+"\nConversion rate = "+rate+" "+toCoin+"\nComission = "+comission+" %\nResult = "+result+" "+toCoin+"\n\n";
        return conversion;
    }
}
