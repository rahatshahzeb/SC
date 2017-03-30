package com.shahzeb.sc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void addFragment(FragmentManager fm, Fragment fragment, int containerId, String tag, boolean addToBackStack, boolean shouldAnimate) {
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment existingFragment = fm.findFragmentByTag(tag);
        if (existingFragment == null) {
            transaction.add(containerId, fragment, tag);
            if (shouldAnimate)
                transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down, R.anim.slide_in_up, R.anim.slide_out_down);
            if (addToBackStack)
                transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    protected void removeFragment(FragmentManager fm, String tag) {
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment existingFragment = fm.findFragmentByTag(tag);
        if (existingFragment != null) {
            transaction.remove(existingFragment);
            transaction.commit();
        }
    }

    protected void replaceFragment(FragmentManager fm, Fragment fragment, int containerId, String tag, boolean addToBackStack, boolean shouldAnimate) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (shouldAnimate) {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(containerId, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }

    protected Fragment getFragmentByTag(FragmentManager fm, String tag) {
        if (fm != null) {
            Fragment fragment = fm.findFragmentByTag(tag);
            return fragment;
        }
        return null;
    }
}
