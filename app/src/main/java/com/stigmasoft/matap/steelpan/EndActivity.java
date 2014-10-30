package com.stigmasoft.matap.steelpan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Alejandro on 28/10/2014.
 */
public class EndActivity extends Activity {

    private TextView textViewEndGame;

    private ImageButton restartButton, fbButton, nextButton;

    private AnimationDrawable animRestartButton, animFbButton, animNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_activity);

        textViewEndGame = (TextView) findViewById(R.id.textViewEndGame);

        //Cargar la fuente
        Typeface tf = Typeface.createFromAsset(getAssets(), "GhandiSans-Bold.ttf");
        textViewEndGame.setTypeface(tf);

        //Asignacion de los botones.
        restartButton = (ImageButton) findViewById(R.id.imageButtonRetry);


        //Asignacion de las animaciones.
        animRestartButton = (AnimationDrawable) restartButton.getBackground();

        //Definición de listeners
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animRestartButton.setVisible(true,true);
                animRestartButton.start();

                Intent i = new Intent(EndActivity.this,GameActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Coger los extras del intent.
        Bundle b = getIntent().getExtras();
        textViewEndGame.setText(b.getString("resultado"));
    }
}
