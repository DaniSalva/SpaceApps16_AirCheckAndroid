package com.spaceapps.aircheck.Fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;

import com.spaceapps.aircheck.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Autor: Daniel Salvador Urgel
 * Fecha: 19/11/2015
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Context context;

    private int[] tabIcons = {
            R.drawable.ic_map,
            R.drawable.ic_stats

    };


    public ViewPagerAdapter(FragmentManager manager,Context context) {
        super(manager);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        Drawable image = ContextCompat.getDrawable(context, tabIcons[position]);
        image.setBounds(0, 0, (int) (image.getIntrinsicHeight()*0.75), (int) (image.getIntrinsicWidth()*0.75));
        Log.d("IMG", "h: " + image.getIntrinsicHeight());
        Log.d("IMG","w: "+image.getIntrinsicWidth());

        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //return mFragmentTitleList.get(position);
        return sb;
    }
}
