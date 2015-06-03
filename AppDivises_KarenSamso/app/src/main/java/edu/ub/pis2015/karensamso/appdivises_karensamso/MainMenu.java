package edu.ub.pis2015.karensamso.appdivises_karensamso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Karen on 03/06/2015.
 */
public class MainMenu extends Activity {

    private Button conversionPage;
    private Button previousPage;
    private Controller controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        conversionPage = (Button) findViewById(R.id.conversion);
        previousPage = (Button) findViewById(R.id.previous);
        controller = (Controller) getApplication();

        controller.load(MainMenu.this);

        conversionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent convert = new Intent(MainMenu.this,MainActivity.class);
                startActivity(convert);
            }
        });

        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previous = new Intent(MainMenu.this,ConversionShow.class);
                startActivity(previous);
            }
        });
    }
}
