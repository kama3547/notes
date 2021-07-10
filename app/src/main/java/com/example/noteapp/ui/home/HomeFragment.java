package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TaskAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        getFromFragment();

        initRecycler();
        return root;
    }

    private void getFromFragment() {
        binding.rvTask.getAdapter();
    }

    private void initRecycler() {
        adapter = new TaskAdapter();
        binding.rvTask.setAdapter(adapter);
        adapter.addModel(new TaskModel("Hello","привет"));
        adapter.addModel(new TaskModel("hi","дарова"));
        adapter.addModel(new TaskModel("How are you?","Как дела?"));
        adapter.addModel(new TaskModel("I'm okay","Отлично"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}