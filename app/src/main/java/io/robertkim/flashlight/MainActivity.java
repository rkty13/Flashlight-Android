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
    Parameters params;
    private boolean isOn = false;

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
}