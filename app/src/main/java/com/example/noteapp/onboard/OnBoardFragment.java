package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.adapter.ViewAdapter;
import com.example.noteapp.databinding.FragmentOnBoardBinding;


public class OnBoardFragment extends Fragment {

    FragmentOnBoardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater(),container,false);
        viewAdd();
        return binding.getRoot();
    }

    private void viewAdd() {
        ViewAdapter viewAdapter = new ViewAdapter(getActivity().getSupportFragmentManager());
        binding.pager.setAdapter(viewAdapter);
    }
}