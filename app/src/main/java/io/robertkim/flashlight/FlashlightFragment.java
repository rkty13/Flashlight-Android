//package io.robertkim.flashlight;
//
//import android.hardware.Camera;
//import android.hardware.Camera.Parameters;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
///**
// * Created by robertkim on 2/4/15.
// */
//public class FlashlightFragment extends Fragment {
//
//    private Button toggleFlashlight;
//    private Camera cam;
//    private Parameters params;
//    private boolean isOn;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//
//        toggleFlashlight = (Button)getView().findViewById(R.id.toggle_button);
//        toggleFlashlight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isOn) {
//                    lightOn();
//                } else {
//                    lightOff();
//                }
//            }
//        });
//        initializeCam();
//        return inflater.inflate(R.layout.activity_main, container, false);
//    }
//
//    public void setCam(Camera cam) {
//        this.cam = cam;
//    }
//
//    public Camera getCam() {
//        return cam;
//    }
//
//    private void lightOn() {
//        params = cam.getParameters();
//        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//        cam.setParameters(params);
//        cam.startPreview();
//        isOn = true;
//    }
//
//    private void lightOff() {
//        params = cam.getParameters();
//        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//        cam.setParameters(params);
//        cam.stopPreview();
//        isOn = false;
//    }
//
//    private void initializeCam() {
//        try {
//            cam = Camera.open();
//            params = cam.getParameters();
//        } catch (RuntimeException e) {
//            Log.e("Camera Error", e.getMessage());
//        }
//    }
//}
