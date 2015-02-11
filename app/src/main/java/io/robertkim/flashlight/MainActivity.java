package io.robertkim.flashlight;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    private Button toggleFlashlight;
    private Camera cam;
    private Parameters params;
    private boolean isOn = false;
    private FlashlightFragment flashlightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        flashlightFragment = (FlashlightFragment) fm.findFragmentByTag("cam");

        if (flashlightFragment == null) {
            flashlightFragment = new FlashlightFragment();
            fm.beginTransaction().add(flashlightFragment, "cam").commit();
            flashlightFragment.setCamera();
        }
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
//Men
