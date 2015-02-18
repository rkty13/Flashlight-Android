package io.robertkim.flashlight;

import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
* Created by robertkim on 2/4/15.
*/
public class FlashlightFragment extends Fragment {

    private Camera cam;
    private Camera.Parameters params;
    private ImageButton toggle;
    private boolean isOn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initializeCam();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_light, parent, false);
        toggle = (ImageButton) v.findViewById(R.id.button_toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOn) {
                    lightOff();
                } else {
                    lightOn();
                }
            }
        });
        return v;
    }

    private void initializeCam() {
        try {
            cam = Camera.open();
            params = cam.getParameters();
        } catch (RuntimeException e) {
            Log.e("Camera Error", e.getMessage());
        }
    }

    private void lightOn() {
        params = cam.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        toggle.setBackgroundColor(Color.parseColor("#ecf0f1"));
        cam.setParameters(params);
        cam.startPreview();
        isOn = true;
    }

    private void lightOff() {
        params = cam.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        toggle.setBackgroundColor(Color.parseColor("#2c3e50"));
        cam.setParameters(params);
        cam.stopPreview();
        isOn = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (cam != null) {
            cam.release();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        toggle.setBackgroundColor(Color.parseColor("#2c3e50"));
        initializeCam();
    }
}