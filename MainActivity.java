package com.example.assignment1_new;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements Fragment1.OnFragment1Listener,
        Fragment2.OnFragment2Listener,
        Fragment3.OnFragment3Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, Fragment1.newInstance())
                    .commit();
        }
    }

    @Override
    public void onGoToFragment2(String name) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Fragment2.newInstance(name))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onGoToFragment3(String name) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Fragment3.newInstance(name))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAgreementChanged(boolean isChecked) {
        // هون الـ Activity هي اللي بتتحكم بالزر عن طريق الـ Interface
        Fragment3 fragment3 = (Fragment3) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        if (fragment3 != null) {
            fragment3.updateButtonState(isChecked);
        }
    }
}