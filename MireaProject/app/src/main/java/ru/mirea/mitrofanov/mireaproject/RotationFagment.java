package ru.mirea.mitrofanov.mireaproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import ru.mirea.mitrofanov.mireaproject.databinding.FragmentRotationBinding;

public class RotationFagment extends Fragment implements SensorEventListener {

    private FragmentRotationBinding binding;

    private ImageView ivDynamic;

    private TextView tvDegree;

    private Float currentDegree = 0f;

    private SensorManager sensorManager;

    private Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRotationBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        mContext = inflater.getContext();
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        init();
        return binding.getRoot();
    }


    private void init() {
        ivDynamic = binding.ivDynamic;
        tvDegree = binding.tvDegree;
        System.out.println("Инициализировано");
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager
                .getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_UI);
        //sensorManager.getOrientation()
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = event.values[2]*100;

        tvDegree.setText(String.format("Отклонение от севера: %s градусов.", (int)degree));
        //Log.d(CompasFragment.class.getSimpleName(), String.format("Degree from North: %s degrees",Float.toString(degree)));
        RotateAnimation ra = new RotateAnimation(
                currentDegree, -degree, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        ra.setDuration(210);
        ra.setFillAfter(true);

        ivDynamic.startAnimation(ra);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}