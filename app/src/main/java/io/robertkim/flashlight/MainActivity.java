package io.robertkim.flashlight;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity {

    private FlashlightFragment flashlightFragment;
    private ImageButton toggle;

    private static final String FLASHLIGHT_FRAGMENT = "flashlight_fragment";
    private static final String KEY_LIGHT_TOGGLE = "light_toggle";

    private static final int onColor = Color.parseColor("#ecf0f1");
    private static final int offColor = Color.parseColor("#2c3e50");

    public boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (ImageButton) findViewById(R.id.button_toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOn) {
                    lightOffWrap(false);
                } else {
                    lightOnWrap(true);
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        flashlightFragment = (FlashlightFragment) fm.findFragmentByTag(FLASHLIGHT_FRAGMENT);

        if (flashlightFragment == null) {
            flashlightFragment = new FlashlightFragment();
            fm.beginTransaction().add(flashlightFragment, FLASHLIGHT_FRAGMENT).commit();
        } else {
            flashlightFragment.initializeCam();
            if (savedInstanceState.getBoolean(KEY_LIGHT_TOGGLE)) {
                lightOnWrap(true);
            } else {
                lightOffWrap(false);
            }
        }
    }

    private void lightOnWrap(boolean switchTo) {
        toggle.setBackgroundColor(onColor);
        toggle.setImageResource(R.drawable.flashlight_yellow);
        new toggleLight().execute(true);
        isOn = switchTo;
    }

    private void lightOffWrap(boolean switchTo) {
        toggle.setBackgroundColor(offColor);
        toggle.setImageResource(R.drawable.flashlight_white);
        new toggleLight().execute(false);
        isOn = switchTo;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_LIGHT_TOGGLE, isOn);
    }

    private class toggleLight extends AsyncTask<Boolean, Void, Void> {

        @Override
        protected Void doInBackground(Boolean... params) {
            if (params[0]) {
                flashlightFragment.lightOn();
            } else {
                flashlightFragment.lightOff();
            }
            return null;
        }
    }
}