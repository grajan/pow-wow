package com.hackathon.pow_wow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Rajan on 6/17/17.
 */

public class SwipeImageAdapter extends FragmentStatePagerAdapter {

    private int[] images;

    public SwipeImageAdapter(FragmentManager fm, int[] images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("image", images[position]);
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public int getCount() {
        return images.length;
    }
}
