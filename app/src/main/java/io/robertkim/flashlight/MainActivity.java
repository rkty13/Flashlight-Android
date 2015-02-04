package io.robertkim.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button toggleFlashlight;
    private Camera cam;
    private Parameters params;
    private boolean isOn = false;
    private static final String KEY_TOGGLE = "camera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            cam = Camera.open();
            params = cam.getParameters();
        } catch (RuntimeException e) {
            Log.e("Camera Error", e.getMessage());
        }

        toggleFlashlight = (Button)findViewById(R.id.toggle_button);
        toggleFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOn) {
                    lightOn();
                } else {
                    lightOff();
                }
            }
        });
        initializeCam();
        if (savedInstanceState != null) {
            isOn = savedInstanceState.getBoolean(KEY_TOGGLE);
            if (isOn) {
                lightOn();
            }
        } else {
            isOn = false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_TOGGLE, isOn);
    }

    private void lightOn() {
        params = cam.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(params);
        cam.startPreview();
        isOn = true;
    }

    private void lightOff() {
        params = cam.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        cam.setParameters(params);
        cam.stopPreview();
        isOn = false;
    }

    private void initializeCam() {
        try {
            cam = Camera.open();
            params = cam.getParameters();
        } catch (RuntimeException e) {
            Log.e("Camera Error", e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cam.release();
        cam = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        cam.release();
        cam = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (cam == null) {
            initializeCam();
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        cam.release();
//        cam = null;
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if (cam == null) {
            initializeCam();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (cam == null) {
            initializeCam();
        }
    }

}
