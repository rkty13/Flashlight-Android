package io.robertkim.flashlight;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
* Created by robertkim on 2/4/15.
*/
public class FlashlightFragment extends Fragment {
    private static final boolean DEBUG = true;
    private static final String TAG = FlashlightFragment.class.getSimpleName();

    private Camera mCam;
    private Camera.Parameters mParams;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initializeCam();
    }

    public void initializeCam() {
        try {
            mCam = Camera.open();
            mParams = mCam.getParameters();
        } catch (RuntimeException e) {
            if (DEBUG) Log.e(TAG, "Camera RuntimeException");
        }
    }

    public void lightOn() {
        if (mCam == null) {
            initializeCam();
        }
        mParams = mCam.getParameters();
        mParams.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCam.setParameters(mParams);
        mCam.startPreview();
    }

    public void lightOff() {
        mParams = mCam.getParameters();
        mParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mCam.setParameters(mParams);
        mCam.stopPreview();
    }

    @Override
    public void onStart() {
        if (DEBUG) Log.i(TAG, "onStart()");
        super.onStart();
        if (mCam == null) {
            initializeCam();
        }
    }

    @Override
    public void onResume() {
        if (DEBUG) Log.i(TAG, "onResume()");
        super.onResume();
        if (mCam == null) {
            initializeCam();
        }
    }

    @Override
    public void onPause() {
        if (DEBUG) Log.i(TAG, "onPause()");
        super.onPause();

        if (mCam != null) {
            mCam.stopPreview();
            mCam.release();
            mCam = null;
        }
    }

    @Override
    public void onStop() {
        if (DEBUG) Log.i(TAG, "onStop()");
        super.onStop();

        if (mCam != null) {
            mCam.stopPreview();
            mCam.release();
            mCam = null;
        }
    }

    @Override
    public void onDestroy() {
        if (DEBUG) Log.i(TAG, "onDestroy()");
        super.onDestroy();

        if (mCam != null) {
            mCam.stopPreview();
            mCam.release();
            mCam = null;
        }
    }
}