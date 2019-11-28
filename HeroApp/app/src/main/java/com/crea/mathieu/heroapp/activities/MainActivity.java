package com.crea.mathieu.heroapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;

import com.crea.mathieu.heroapp.R;
import com.crea.mathieu.heroapp.fragments.SearchHeroFragment;

public class MainActivity extends AppCompatActivity {

    public static Context MAIN_CONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MAIN_CONTEXT = this;

        setContentView(R.layout.activity_main);
        startFirstFragment();
    }

    private void startFirstFragment() {

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container_fragment, new SearchHeroFragment(), "SearchHeroFragment").commit();
    }
}
