package edu.ub.pis2015.karensamso.appdivises_karensamso;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Karen on 03/06/2015.
 */
public class ConversionShow extends Activity {

    private TextView text;
    private Controller controller;
    private ArrayList<PreviousConversion> previous;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_conversions);

        text = (TextView) findViewById(R.id.list);
        controller = (Controller) getApplication();
        previous = controller.getConversionList();

        String total = "";

        for(int i=0; i< previous.size(); i++){
            total = total + previous.get(i).toString();
        }
        Log.i("mida de la llista", String.valueOf(previous.size()));

        text.setText(total);
    }
}
