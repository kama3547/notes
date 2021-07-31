package com.example.noteapp.ui.form;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentFormBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.utils.MyApp;

import java.util.concurrent.atomic.AtomicBoolean;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private TaskModel taskModel;
    public static final String Bundle_KEY = "text";
    public static final String RESULT_KEY = "result";

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
            String text = binding.titleEt.getText().toString().trim();
            String title = binding.titleEt.getText().toString();
            if (TextUtils.isEmpty(text)){
                binding.titleEt.setError("Input text correctly");
            }
            if (taskModel == null){


                taskModel = new  TaskModel(title, "sdfds");
                MyApp.getInstance().noteDao().insert(taskModel);

            }
            navController.navigateUp();
        });
    }
}