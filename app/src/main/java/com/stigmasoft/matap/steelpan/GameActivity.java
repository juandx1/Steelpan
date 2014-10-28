package com.stigmasoft.matap.steelpan;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Juan Manuel on 21/10/2014.
 */
public class GameActivity extends Activity implements SensorEventListener{

    private long lastUpdate = 0;

    private float last_x, last_y, last_z;

    private static final int SENSITIVITY = 600;

    private AnimationDrawable upAnimation,downAnimation,leftAnimation,rigthAnimation, acelAnimation;

    private ImageButton buttonLeft, buttonUp, buttonRigth, buttonDown;

    private ImageView imageViewLeft;

    private SensorManager senSensorManager;

    private Sensor senAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonUp = (ImageButton) findViewById(R.id.buttonUp);
        buttonRigth = (ImageButton) findViewById(R.id.buttonRigth);
        buttonDown = (ImageButton) findViewById(R.id.buttonDown);

        downAnimation = (AnimationDrawable) buttonDown.getBackground();
        upAnimation = (AnimationDrawable) buttonUp.getBackground();
        rigthAnimation = (AnimationDrawable) buttonRigth.getBackground();
        leftAnimation = (AnimationDrawable) buttonLeft.getBackground();

        imageViewLeft = (ImageView) findViewById(R.id.imageViewLeft);

        acelAnimation = (AnimationDrawable) imageViewLeft.getBackground();
        acelAnimation.start();

        final MediaPlayer mediaPlayerA = MediaPlayer.create(GameActivity.this, R.raw.a1);
        final MediaPlayer mediaPlayerB = MediaPlayer.create(GameActivity.this, R.raw.b);
        final MediaPlayer mediaPlayerC = MediaPlayer.create(GameActivity.this, R.raw.c);
        final MediaPlayer mediaPlayerF = MediaPlayer.create(GameActivity.this, R.raw.f);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaPlayerA.start();
                    upAnimation.setVisible(true, true);
                    upAnimation.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerB.start();
                downAnimation.setVisible(true, true);
                downAnimation.start();
            }
        });

        buttonRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerC.start();
                rigthAnimation.setVisible(true, true);
                rigthAnimation.start();
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerF.start();
                leftAnimation.setVisible(true, true);
                leftAnimation.start();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SENSITIVITY) {
                    acelAnimation.stop();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }
}
