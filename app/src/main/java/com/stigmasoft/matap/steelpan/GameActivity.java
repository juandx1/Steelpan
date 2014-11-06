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
import android.util.Log;
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
public class GameActivity extends Activity implements SensorEventListener {

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SENSITIVITY = 1000;

    private ArrayList<Integer> botones;

    //Media Players
    private MediaPlayer mediaPlayerGS;
    private MediaPlayer mediaPlayerG;
    private MediaPlayer mediaPlayerFS;
    private MediaPlayer player;
    private MediaPlayer mediaPlayerE;
    private MediaPlayer mediaPlayerDS;
    private MediaPlayer mediaPlayerD;
    private MediaPlayer mediaPlayerCS;
    private MediaPlayer mediaPlayerC;
    private MediaPlayer mediaPlayerB;
    private MediaPlayer mediaPlayerA;
    private MediaPlayer mediaPlayerError;

    //Animaciones
    private AnimationDrawable upAnimation, downAnimation, leftAnimation, rigthAnimation, acelAnimation;

    //botones
    private ImageButton buttonLeft, buttonUp, buttonRigth, buttonDown;

    //Sensor
    private ImageView imageViewLeft;

    private SensorManager senSensorManager;

    private Sensor senAccelerometer;

    //....
    private CountDownTimer timer;

    private FragmentoCancion fragmentoCancion;

    private ArrayList<Nota> notas;

    private int indice = 0, contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        Bundle bundle = getIntent().getExtras();
        fragmentoCancion = (FragmentoCancion) bundle.get("fragmento");
        notas = fragmentoCancion.getListaNotas();
        indice = 0;
        botones = new ArrayList<Integer>();

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

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

        mediaPlayerA = MediaPlayer.create(GameActivity.this, R.raw.a);
        mediaPlayerB = MediaPlayer.create(GameActivity.this, R.raw.b);
        mediaPlayerC = MediaPlayer.create(GameActivity.this, R.raw.c);
        mediaPlayerCS = MediaPlayer.create(GameActivity.this, R.raw.c_sharp);
        mediaPlayerD = MediaPlayer.create(GameActivity.this, R.raw.d);
        mediaPlayerDS = MediaPlayer.create(GameActivity.this, R.raw.d_sharp);
        mediaPlayerE = MediaPlayer.create(GameActivity.this, R.raw.e);
        player = MediaPlayer.create(GameActivity.this, R.raw.f);
        mediaPlayerFS = MediaPlayer.create(GameActivity.this, R.raw.f_sharp);
        mediaPlayerG = MediaPlayer.create(GameActivity.this, R.raw.g);
        mediaPlayerGS = MediaPlayer.create(GameActivity.this, R.raw.g_sharp);
        mediaPlayerError = MediaPlayer.create(GameActivity.this,R.raw.error);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = new CountDownTimer(fragmentoCancion.getDuracion(), 10) {
            @Override
            public void onTick(long l) {
                Log.d("tiempo", l + "");
                Nota notaActual = notas.get(indice);
                Log.d("notas", notas.size()+"");
                int numero = numeroRandom();
                AnimationDrawable animation = animationRandom(numero);
                MediaPlayer player = darMediaPlayer(notaActual);
                if (notaActual.getTiempoDeEspera() <= fragmentoCancion.getDuracion() - l) {

                    if (player.isPlaying()) {
                        player.pause();
                        player.seekTo(0);
                    }
                    player.start();
                    animation.setVisible(true, true);
                    animation.start();
                    botones.add(numero);
                    indice++;
                    Log.d("indice", indice + "");
                }
                if (indice == notas.size()) {
                    Log.d("entre", "entre");
                    timer.onFinish();
                    timer.cancel();
                }
            }

            @Override
            public void onFinish() {
                buttonUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer player = darMediaPlayer(notas.get(contador));

                        upAnimation.setVisible(true, true);
                        upAnimation.start();

                        if (botones.get(contador) == 0) {
                            if (player.isPlaying()) {
                                player.pause();
                                player.seekTo(0);
                            }
                            player.start();
                            if (contador == botones.size() - 1) {
                                Intent i = new Intent(GameActivity.this, EndActivity.class);
                                i.putExtra("fragmento", fragmentoCancion);
                                i.putExtra("resultado", getString(R.string.nivel_completado));
                                startActivity(i);
                            } else {
                                contador++;
                            }
                        } else {
                            if (mediaPlayerError.isPlaying()) {
                                mediaPlayerError.pause();
                                mediaPlayerError.seekTo(0);
                            }
                            mediaPlayerError.start();
                            Intent i = new Intent(GameActivity.this, EndActivity.class);
                            i.putExtra("fragmento", fragmentoCancion);
                            i.putExtra("resultado", getString(R.string.fallaste));
                            startActivity(i);
                        }
                    }
                });

                buttonDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer player = darMediaPlayer(notas.get(contador));
                        downAnimation.setVisible(true, true);
                        downAnimation.start();

                        if (botones.get(contador) == 2) {
                            if (player.isPlaying()) {
                                player.pause();
                                player.seekTo(0);
                            }
                            player.start();
                            if (contador == botones.size() - 1) {
                                Intent i = new Intent(GameActivity.this, EndActivity.class);
                                i.putExtra("fragmento", fragmentoCancion);
                                i.putExtra("resultado", getString(R.string.nivel_completado));
                                startActivity(i);
                            } else {
                                contador++;
                            }
                        } else {
                            if (mediaPlayerError.isPlaying()) {
                                mediaPlayerError.pause();
                                mediaPlayerError.seekTo(0);
                            }
                            mediaPlayerError.start();
                            Intent i = new Intent(GameActivity.this, EndActivity.class);
                            i.putExtra("fragmento", fragmentoCancion);
                            i.putExtra("resultado", getString(R.string.fallaste));
                            startActivity(i);
                        }
                    }
                });

                buttonRigth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer player = darMediaPlayer(notas.get(contador));
                        rigthAnimation.setVisible(true, true);
                        rigthAnimation.start();

                        if (botones.get(contador) == 3) {
                            if (player.isPlaying()) {
                                player.pause();
                                player.seekTo(0);
                            }
                            player.start();
                            if (contador == botones.size() - 1) {
                                Intent i = new Intent(GameActivity.this, EndActivity.class);
                                i.putExtra("fragmento", fragmentoCancion);
                                i.putExtra("resultado", getString(R.string.nivel_completado));
                                startActivity(i);
                            } else {
                                contador++;
                            }
                        } else {
                            if (mediaPlayerError.isPlaying()) {
                                mediaPlayerError.pause();
                                mediaPlayerError.seekTo(0);
                            }
                            mediaPlayerError.start();
                            Intent i = new Intent(GameActivity.this, EndActivity.class);
                            i.putExtra("fragmento", fragmentoCancion);
                            i.putExtra("resultado", getString(R.string.fallaste));
                            startActivity(i);
                        }
                    }
                });

                buttonLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer player = darMediaPlayer(notas.get(contador));
                        leftAnimation.setVisible(true, true);
                        leftAnimation.start();
                        if (botones.get(contador) == 1) {
                            if (player.isPlaying()) {
                                player.pause();
                                player.seekTo(0);
                            }
                            player.start();
                            if (contador == botones.size() - 1) {
                                Intent i = new Intent(GameActivity.this, EndActivity.class);
                                i.putExtra("fragmento", fragmentoCancion);
                                i.putExtra("resultado", getString(R.string.nivel_completado));
                                startActivity(i);
                            } else {
                                contador++;
                            }
                        } else {
                            if (mediaPlayerError.isPlaying()) {
                                mediaPlayerError.pause();
                                mediaPlayerError.seekTo(0);
                            }
                            mediaPlayerError.start();
                            Intent i = new Intent(GameActivity.this, EndActivity.class);
                            i.putExtra("fragmento", fragmentoCancion);
                            i.putExtra("resultado", getString(R.string.fallaste));
                            startActivity(i);
                        }
                    }
                });

            }
        }.start();
    }

    private int numeroRandom(){
        Random random = new Random();
        return random.nextInt(4);
    }

    private AnimationDrawable animationRandom(int numero) {
        if (numero == 0) {
            return upAnimation;
        } else if (numero == 1) {
            return leftAnimation;
        } else if (numero == 2) {
            return downAnimation;
        } else {
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
                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SENSITIVITY) {
                    acelAnimation.stop();

                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    public MediaPlayer darMediaPlayer(Nota nota){
        if (nota.getNota().equals("A")) {
            return mediaPlayerA;
        } else if (nota.getNota().equals("B")) {
            return mediaPlayerB;
        } else if (nota.getNota().equals("C")) {
            return mediaPlayerC;
        } else if (nota.getNota().equals("CS")) {
            return mediaPlayerCS;
        } else if (nota.getNota().equals("D")) {
            return mediaPlayerD;
        } else if (nota.getNota().equals("DS")) {
            return mediaPlayerDS;
        } else if (nota.getNota().equals("E")) {
            return mediaPlayerE;
        } else if (nota.getNota().equals("F")) {
            return player;
        } else if (nota.getNota().equals("FS")) {
            return mediaPlayerFS;
        } else if (nota.getNota().equals("G")) {
            return mediaPlayerG;
        } else if (nota.getNota().equals("GS")) {
            return mediaPlayerGS;
        }
        return null;
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
