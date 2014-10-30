package com.stigmasoft.matap.steelpan;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Alejandro on 28/10/2014.
 */
public class EndActivity extends Activity {

    private TextView textViewEndGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_activity);
        textViewEndGame = (TextView) findViewById(R.id.textViewEndGame);
        Typeface tf = Typeface.createFromAsset(getAssets(), "GhandiSans-Bold.ttf");
        textViewEndGame.setTypeface(tf);

        Bundle b = getIntent().getExtras();

        textViewEndGame.setText(b.getString("resultado"));
    }
}
