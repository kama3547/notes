package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noteapp.R;
import com.example.noteapp.adapter.TaskAdapter;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.utils.MyApp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ArrayList<TaskModel> list = new ArrayList<>();
    boolean linear = true;
    private FragmentHomeBinding binding;
    TaskAdapter adapter = new TaskAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setupRecycler();
        getdata();
        searchtitle();
        getNotesFromDB();
        return binding.getRoot();
    }

    private void getNotesFromDB() {
        MyApp.instance.noteDao().getAll().observe(getViewLifecycleOwner(), list->{
        });
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
    private void setupRecycler() {
        if (!linear){
            binding.rvTask.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }else {
            binding.rvTask.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        binding.rvTask.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId()== R.id.action_dash){
            if (linear){
                binding.rvTask.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                item.setIcon(R.drawable.ic_dash);
                linear = false;
            }else {
                binding.rvTask.setLayoutManager(new LinearLayoutManager(getContext()));
                item.setIcon(R.drawable.ic_bullet);
                linear = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}