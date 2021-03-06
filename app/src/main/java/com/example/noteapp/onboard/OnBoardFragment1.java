package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentOnBoard1Binding;
import com.example.noteapp.databinding.FragmentOnBoardBinding;
import com.example.noteapp.utils.PrefrencesHelper;

public class OnBoardFragment1 extends Fragment {

    private FragmentOnBoard1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoard1Binding.inflate(inflater,container,false);
        setClicklistener();
        return binding.getRoot();
    }
    private void setClicklistener() {
        binding.txt1.setOnClickListener(v -> {
            PrefrencesHelper prefrencesHelper = new PrefrencesHelper();
            prefrencesHelper.init(requireContext());
            prefrencesHelper.onSaveOnBoardState();
            NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });
    }
}