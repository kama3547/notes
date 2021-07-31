package com.example.noteapp.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import org.jetbrains.annotations.NotNull;

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

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            taskModel = (TaskModel) getArguments().getSerializable("task");
            if (taskModel != null){
                binding.titleEt.setText(taskModel.getTitle());
            }
        }
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
            else {
                taskModel.setTitle(title);
                MyApp.getInstance().noteDao().update(taskModel);
            }
            navController.navigateUp();
        });
    }
}