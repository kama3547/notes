package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.noteapp.adapter.ViewAdapter;
import com.example.noteapp.databinding.FragmentOnBoardBinding;
import com.google.android.material.tabs.TabLayoutMediator;


public class OnBoardFragment extends Fragment {

    FragmentOnBoardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewAdd();

    }

    private void viewAdd() {
        ViewAdapter viewAdapter = new ViewAdapter(getActivity()
                .getSupportFragmentManager(),getLifecycle());
        binding.pager.setAdapter(viewAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {}).attach();
    }
}