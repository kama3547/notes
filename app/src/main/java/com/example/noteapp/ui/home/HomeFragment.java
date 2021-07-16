package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.noteapp.adapter.TaskAdapter;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.model.TaskModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<TaskModel> list = new ArrayList<>();
    private FragmentHomeBinding binding;

    TaskAdapter adapter = new TaskAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setupRecycler();
        getdata();
        searchtitle();
        return binding.getRoot();
    }

    private void setupRecycler() {
        binding.rvTask.setAdapter(adapter);
    }
    private void getdata() {
        getParentFragmentManager().setFragmentResultListener("txt",getViewLifecycleOwner(),(requestKey, result) ->  {
            TaskModel model = (TaskModel) result.getSerializable("key");
            list.add(model);
            adapter.addModel(model);
        });

    }
    private  void searchtitle(){
        binding.edTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });
    }
    private void filter (String text) {
        ArrayList<TaskModel> filteredList = new ArrayList<>();
        for (TaskModel item : list) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}