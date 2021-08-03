package com.example.noteapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.noteapp.onboard.OnBoardFragment1;
import com.example.noteapp.onboard.OnBoardFragment2;
import com.example.noteapp.onboard.OnBoardFragment3;

import org.jetbrains.annotations.NotNull;

public class ViewAdapter extends FragmentStateAdapter {


    public ViewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new OnBoardFragment1();
            case 1:
                return new OnBoardFragment2();
            case 2:
                return new OnBoardFragment3();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}