package com.crea.mathieu.heroapp.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.crea.mathieu.heroapp.R;

public class UtilNavigationFragment {

    public static void nextFragment(Fragment currentFragment, FragmentActivity context){

        FragmentManager fm = context.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container_fragment, currentFragment, currentFragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
