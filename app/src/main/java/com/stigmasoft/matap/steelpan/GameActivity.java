package com.stigmasoft.matap.steelpan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import mundo.FragmentoCancion;
import mundo.Nota;

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

    private CountDownTimer timer;

    private FragmentoCancion fragmentoCancion;

    private ArrayList<Nota> notas;

    private int indice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        Bundle bundle = getIntent().getExtras();
        fragmentoCancion = (FragmentoCancion) bundle.get("fragmento");
        notas = fragmentoCancion.getListaNotas();


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

        final MediaPlayer mediaPlayerA = MediaPlayer.create(GameActivity.this, R.raw.a);
        final MediaPlayer mediaPlayerB = MediaPlayer.create(GameActivity.this, R.raw.b);
        final MediaPlayer mediaPlayerC = MediaPlayer.create(GameActivity.this, R.raw.c);
        final MediaPlayer mediaPlayerCS = MediaPlayer.create(GameActivity.this, R.raw.c_sharp);
        final MediaPlayer mediaPlayerD = MediaPlayer.create(GameActivity.this, R.raw.d);
        final MediaPlayer mediaPlayerDS = MediaPlayer.create(GameActivity.this, R.raw.d_sharp);
        final MediaPlayer mediaPlayerE = MediaPlayer.create(GameActivity.this, R.raw.e);
        final MediaPlayer mediaPlayerF = MediaPlayer.create(GameActivity.this, R.raw.f);
        final MediaPlayer mediaPlayerFS = MediaPlayer.create(GameActivity.this, R.raw.f_sharp);
        final MediaPlayer mediaPlayerG = MediaPlayer.create(GameActivity.this, R.raw.g);
        final MediaPlayer mediaPlayerGS = MediaPlayer.create(GameActivity.this, R.raw.g_sharp);

        timer = new CountDownTimer(fragmentoCancion.getDuracion(),100) {
            @Override
            public void onTick(long l) {
                Nota nota = notas.get(indice);
                if(nota.getTiempoDeEspera() == l){

                    if(nota.getNota().equals("A"))
                    {
                        if(mediaPlayerA.isPlaying()){
                            mediaPlayerA.pause();
                            mediaPlayerA.seekTo(0);
                        }
                        mediaPlayerA.start();
                        AnimationDrawable animation = buttonRandom();
                        animation.setVisible(true, true);
                        animation.start();
                    }
                    if(nota.getNota().equals("B"))
                    {
                        if(mediaPlayerB.isPlaying()){
                            mediaPlayerB.pause();
                            mediaPlayerB.seekTo(0);
                        }
                        mediaPlayerB.start();
                        AnimationDrawable animation = buttonRandom();
                        animation.setVisible(true, true);
                        animation.start();
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(mediaPlayerA.isPlaying()){
                        mediaPlayerA.pause();
                        mediaPlayerA.seekTo(0);
                    }
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
                if(mediaPlayerB.isPlaying()){
                    mediaPlayerB.pause();
                    mediaPlayerB.seekTo(0);
                }
                mediaPlayerB.start();
                downAnimation.setVisible(true, true);
                downAnimation.start();
            }
        });

        buttonRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayerC.isPlaying()){
                    mediaPlayerC.pause();
                    mediaPlayerC.seekTo(0);
                }
                mediaPlayerC.start();
                rigthAnimation.setVisible(true, true);
                rigthAnimation.start();
            }
        });

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayerF.isPlaying()){
                    mediaPlayerF.pause();
                    mediaPlayerF.seekTo(0);
                }
                mediaPlayerF.start();
                leftAnimation.setVisible(true, true);
                leftAnimation.start();
            }
        });

    }

    private AnimationDrawable buttonRandom(){
        Random random = new Random();
        int numero = random.nextInt(4);
        if(numero == 0){
            return upAnimation;
        }else if(numero == 1){
            return leftAnimation;
        }else if(numero == 2){
            return downAnimation;
        }else{
            return rigthAnimation;
        }
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

                    Intent i = new Intent(this,EndActivity.class);
                    if(false){
                        i.putExtra("resultado",getString(R.string.nivel_completado));
                    }else
                    {
                        i.putExtra("resultado",getString(R.string.fallaste));
                    }
                    startActivity(i);
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
