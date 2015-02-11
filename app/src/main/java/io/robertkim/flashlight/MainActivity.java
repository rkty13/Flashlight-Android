package io.robertkim.flashlight;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {

    private FlashlightFragment flashlightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        flashlightFragment = (FlashlightFragment) fm.findFragmentById(R.id.fragmentContainer);

        if (flashlightFragment == null) {
            flashlightFragment = new FlashlightFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, flashlightFragment)
                    .commit();

        }

    }
}
