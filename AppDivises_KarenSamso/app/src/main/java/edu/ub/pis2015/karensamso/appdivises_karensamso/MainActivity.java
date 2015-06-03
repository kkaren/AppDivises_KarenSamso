package edu.ub.pis2015.karensamso.appdivises_karensamso;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;


public class MainActivity extends Activity implements Serializable{

    // Declaration of the app's elements
    private TextView textR;
    private RadioButton selectEuro;
    private RadioButton selectDollar;
    private EditText editTextQ;
    private EditText editTextC;
    private EditText editTextR;
    private TextView textEuro;
    private TextView textDollar;
    private TextView text1Euro;
    private TextView textDollar2;
    private Button convert;
    private RadioGroup radioGroup;

    private PreviousConversion conv;
    private Controller controller;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        selectEuro = (RadioButton) findViewById(R.id.radioButtonEuro);
        selectDollar = (RadioButton) findViewById(R.id.radioButtonDollar);
        convert = (Button) findViewById(R.id.convertButton);
        editTextQ = (EditText) findViewById(R.id.editTextQuant);
        editTextC = (EditText) findViewById(R.id.editTextCom);
        editTextR = (EditText) findViewById(R.id.editTextConv);
        textR = (TextView) findViewById(R.id.resultText);
        textEuro = (TextView) findViewById(R.id.signEuro);
        textDollar = (TextView) findViewById(R.id.signDollar);
        text1Euro = (TextView) findViewById(R.id.sign1Euro);
        textDollar2 = (TextView) findViewById(R.id.signDollar2);

        controller = (Controller) getApplication();

        convert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                double result = 0.00;
                DecimalFormat df = new DecimalFormat("0.00");

                // We get the digits the user entered
                String quantS = editTextQ.getText().toString();
                String commissS = editTextC.getText().toString();
                String rateS = editTextR.getText().toString();

                // We check if those are valid, if not we use the default values
                if (quantS.equals("")) {
                    quantS = editTextQ.getHint().toString();
                }
                if (commissS.equals("")) {
                    commissS = editTextC.getHint().toString();
                }
                if (rateS.equals("")) {
                    rateS = editTextR.getHint().toString();
                }

                float quant = Float.parseFloat(quantS);
                float commiss = Float.parseFloat(commissS);
                double rate = Double.parseDouble(rateS);

                // We apply the conversion rate and the commission
                if (commiss >= 0 && commiss <= 5) {
                    result = (quant * rate) - ((commiss / 100) * quant);

                    // We display the result on the text field
                    String res = df.format(result);
                    textR.setText(res);


                    conv = new PreviousConversion(quant,textEuro.getText().toString(),rate,textDollar2.getText().toString(),commiss,res);
                    controller.addConversion(conv);
                    controller.save(MainActivity.this);
                } else {
                    //We show a toast asking to introduce the commission correctly
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });

            // We change the signs of the coin, the rates, etc, when the selection is changed
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (selectDollar.isChecked()) {
                        textEuro.setText(R.string.dollarSign);
                        textDollar.setText(R.string.euroSign);
                        text1Euro.setText(R.string.dollar1Sign);
                        textDollar2.setText(R.string.euroSign);
                        editTextR.setHint(R.string.dollarRate);
                    } else {
                        textEuro.setText(R.string.euroSign);
                        textDollar.setText(R.string.dollarSign);
                        text1Euro.setText(R.string.euro1Sign);
                        textDollar2.setText(R.string.dollarSign);
                        editTextR.setHint(R.string.euroRate);
                    }

                    // All the texts fields reset when the type of conversion is changed
                    editTextQ.getText().clear();
                    editTextC.getText().clear();
                    textR.setText(textR.getHint().toString());
                    editTextR.getText().clear();

                }
            });
    }

    // We use this method to preserve the result when the screen is rotated
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String res = textR.getText().toString();
        outState.putString(String.valueOf(R.string.guardar), res);
    }

    // We use this method to preserve the result when the screen is rotated
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String res = savedInstanceState.getString(String.valueOf(R.string.guardar));
        textR.setText(res);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
