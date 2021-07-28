package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentOnBoard3Binding;
import com.example.noteapp.utils.PrefrencesHelper;


public class OnBoardFragment3 extends Fragment {

     private FragmentOnBoard3Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoard3Binding.inflate(inflater,container,false);
        setClickListener();
        return binding.getRoot();
    }
    private void setClickListener() {
        binding.fn.setOnClickListener(v -> {
            PrefrencesHelper prefrencesHelper = new PrefrencesHelper();
            prefrencesHelper.init(requireContext());
            prefrencesHelper.onSaveOnBoardState();
            NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });
    }
}