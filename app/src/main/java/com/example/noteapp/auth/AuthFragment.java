package com.example.noteapp.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentAuthBinding;
import com.google.firebase.auth.FirebaseAuth;


public class AuthFragment extends Fragment {

    FragmentAuthBinding binding;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthBinding.inflate(getLayoutInflater(),container,false);
        mAuth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }
}