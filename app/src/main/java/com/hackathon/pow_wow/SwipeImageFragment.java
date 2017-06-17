package com.hackathon.pow_wow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SwipeImageFragment extends Fragment {

    private ViewPager mPager;

    private PagerAdapter mPageAdapter;

    private int[] images;

    /**
     * The activity that holds the fragment should implement SwipeFragmentInterface
     */
    public SwipeImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SwipeFragmentInterface sfi = (SwipeFragmentInterface) getActivity();
        images = sfi.getImageArray();

        View view = inflater.inflate(R.layout.fragment_swipe_image, container, false);
        Log.i("View", "onCreate: "+ view);
        mPager = (ViewPager) view.findViewById(R.id.swipeView);
        mPageAdapter = new SwipeImageAdapter(getActivity().getSupportFragmentManager(), images);
        mPager.setAdapter(mPageAdapter);

        return view;
    }

}

interface SwipeFragmentInterface {

    int[] getImageArray();
}