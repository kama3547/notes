package com.example.noteapp.ui.form;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentFormBinding;
import com.example.noteapp.model.TaskModel;


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
        binding.someId.setOnClickListener(v -> {
            String title = binding.titleEt.getText().toString();
            taskModel = new  TaskModel(title);
            Bundle bundle = new Bundle();
            bundle.putSerializable("key",taskModel);
            getParentFragmentManager().setFragmentResult("txt",bundle);
            navController.navigateUp();
        });
    }
}