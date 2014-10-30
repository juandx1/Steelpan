package com.stigmasoft.matap.steelpan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Juan Manuel on 30/09/2014.
 */
public class MainActivity extends Activity {

    /**
     * Atributos text view titulo de la aplicacion.
     */
    private TextView textViewAppName;
    private TextView textViewChallenge;

    /**
     * Atributo boton de inicio al lobby
     */
    private Button buttonMainLayoutStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Typeface tf = Typeface.createFromAsset(getAssets(), "GhandiSans-Bold.ttf");

        //Obtener los textview
        textViewAppName = (TextView) findViewById(R.id.textViewAppName);
        textViewChallenge = (TextView) findViewById(R.id.textViewChallenge);

        buttonMainLayoutStart = (Button) findViewById(R.id.buttonMainStart);
        buttonMainLayoutStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });

        AdminBD bd = new AdminBD(this);

        //Poner fuente textview
        textViewAppName.setTypeface(tf);
        textViewChallenge.setTypeface(tf);
        buttonMainLayoutStart.setTypeface(tf);

    }

}
