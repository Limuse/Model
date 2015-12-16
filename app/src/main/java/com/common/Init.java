package com.common;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.fragment.Fragment1;
import com.fragment.Fragment2;
import com.fragment.Fragment3;
import com.fragment.Fragment4;
import com.fragment.FragmentMy;

/**
 * Created by admin on 2015/12/14.
 */
public class Init {

    private static Fragment1 fragment1=new Fragment1();
    private static Fragment2 fragment2=new Fragment2();
    private static Fragment3 fragment3=new Fragment3();
    private static Fragment4 fragment4=new Fragment4();
    private static FragmentMy fragmentMy=new FragmentMy();

    public static Fragment[] setHomeFragment(int count) {
        Fragment[] fragments;
        switch (count) {
            case 1:
                fragments = new Fragment[]{fragment1};
                return fragments;
            case 2:
                fragments = new Fragment[]{fragment1,fragmentMy};
                return fragments;
            case 3:
                fragments = new Fragment[]{fragment1,fragment2,fragmentMy};
                return fragments;
            case 4:
                fragments = new Fragment[]{fragment1,fragment2,fragment3,fragmentMy};
                return fragments;
            case 5:
                fragments = new Fragment[]{fragment1,fragment2,fragment3,fragment4,fragmentMy};
                return fragments;
            default:
                break;
        }
        return null;
    }

}
