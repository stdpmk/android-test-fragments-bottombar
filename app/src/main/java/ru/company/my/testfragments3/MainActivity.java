package ru.company.my.testfragments3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_main_container, MainFragment.newInstance())
                    .commit();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
