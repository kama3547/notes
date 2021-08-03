package com.example.noteapp.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentSignInBinding;
import com.example.noteapp.databinding.FragmentSignInBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SighInFragment extends Fragment {
    FragmentSignInBinding binding;
    FirebaseAuth sAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater(),container,false);
        sAuth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }
}