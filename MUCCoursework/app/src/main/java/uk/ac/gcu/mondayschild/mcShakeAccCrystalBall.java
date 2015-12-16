package uk.ac.gcu.mondayschild;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by rla on 12/11/2014.
 */
public class mcShakeAccCrystalBall extends Activity implements SensorEventListener{
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600; // Use to increase or decrease the sensitivity

    String futureList[] = { "He who lives by the crystal ball soon learns to eat ground glass.",
            "I'm not in the business of reading tea leaves. I don't have a crystal ball.",
            "Your future looks bright when you switch on the light!",
            "The mists of time are obscuring my view!",
            "Those who have knowledge, don't predict. Those who predict, don't have knowledge.",
            "Prediction is very difficult, especially if it's about the future.",
            "A good forecaster is not smarter than everyone else, he merely has his ignorance better organised.",
            "To expect the unexpected shows a thoroughly modern intellect.",
            "If you have to forecast, forecast often.",
            "It is said that the present is pregnant with the future."

    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_crystalball_output);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        Random randNumber = new Random();
        int iNumber = randNumber.nextInt(10) + 1;

        TextView futureText = (TextView)findViewById(R.id.cb_out_msg);
        futureText.setText(""+futureList[iNumber]);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Random randNumber = new Random();
                    int iNumber = randNumber.nextInt(10) + 1;

                    TextView futureText = (TextView)findViewById(R.id.cb_out_msg);
                    futureText.setText(""+futureList[iNumber]);
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

 }