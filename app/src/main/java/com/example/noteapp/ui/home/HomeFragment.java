package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noteapp.R;
import com.example.noteapp.adapter.TaskAdapter;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.onitemclicklistener.OnItemClickListener;
import com.example.noteapp.utils.MyApp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String RESULT_KEY = "text" ;
    private static final String Bundle_KEY = "" ;
    private List<TaskModel> list = new ArrayList<>();
    boolean linear = true;
    private FragmentHomeBinding binding;
    TaskModel taskModel;
    TaskAdapter adapter;
    NavController navController;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setupRecycler();
        delete();
        getData();
        searchtitle();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, TaskModel taskModel) {
                Bundle bundle = new Bundle();
                bundle.putInt("pos",position);
                bundle.putSerializable("task", taskModel);
                navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_nav_home_to_formFragment,bundle);
                Log.e("TAG", "onItemClick: " + taskModel.getTitle() );

            }
        });
        return binding.getRoot();
    }




    private void getData() {
        getParentFragmentManager().setFragmentResultListener(RESULT_KEY,getViewLifecycleOwner(),((requestKey, result) -> {
            String text = result.getString(Bundle_KEY);
            adapter.addModel(new TaskModel(text," "));
        }));
        MyApp.getInstance().noteDao().getAll().observe(requireActivity(), new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                adapter.setList(taskModels);
                list = taskModels;
            }
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
    private void delete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                MyApp.getInstance().noteDao().delete(list.get(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(binding.rvTask);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}