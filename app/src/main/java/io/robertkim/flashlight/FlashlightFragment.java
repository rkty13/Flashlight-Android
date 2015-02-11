package io.robertkim.flashlight;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
* Created by robertkim on 2/4/15.
*/
public class FlashlightFragment extends Fragment {

    private Camera cam;
    private Camera.Parameters params;
    private Button toggle;
    private boolean isOn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeCam();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_light, parent, false);
        toggle = (Button) v.findViewById(R.id.button_toggle);
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