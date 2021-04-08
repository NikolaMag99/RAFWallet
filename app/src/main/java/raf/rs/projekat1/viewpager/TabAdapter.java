package raf.rs.projekat1.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import raf.rs.projekat1.fragments.Liste;
import raf.rs.projekat1.fragments.Prihodi;
import raf.rs.projekat1.fragments.Profil;
import raf.rs.projekat1.fragments.Rashodi;
import raf.rs.projekat1.fragments.Stanje;
import raf.rs.projekat1.fragments.Unos;


public class TabAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 2;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;


    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_1: fragment = new Prihodi(); break;
            default: fragment = new Rashodi(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "PRIHODI";
            default: return "RASHODI";
        }
    }
}
