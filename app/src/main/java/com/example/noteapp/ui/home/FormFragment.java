package com.example.noteapp.ui.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentFormBinding;
import com.example.noteapp.databinding.FragmentHomeBinding;


public class FormFragment extends Fragment {
   TaskModel taskModel;
    private FragmentFormBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);




        initClickListener(navController);
        return root;
    }

    private void initClickListener(NavController navController) {
        String tit = binding.titleEt.getText().toString();
        String dec = binding.descEt.getText().toString();
        Bundle bundle = new Bundle();
        binding.btnSave.setOnClickListener(v -> {
            taskModel = new TaskModel(tit,dec );
            bundle.putSerializable("kamil", taskModel);

            navController.navigateUp();
        });
    }


}