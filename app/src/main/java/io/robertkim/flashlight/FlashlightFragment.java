package io.robertkim.flashlight;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;

/**
* Created by robertkim on 2/4/15.
*/
public class FlashlightFragment extends Fragment {

    private Button toggleFlashlight;
    private Camera cam;
    private Camera.Parameters params;
    private boolean isOn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setCamera(Camera cam) {
        this.cam = cam;
    }

    public Camera getCamera() {
        return cam;
    }
}