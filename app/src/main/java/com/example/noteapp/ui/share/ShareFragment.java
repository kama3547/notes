package com.example.noteapp.ui.share;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.databinding.FragmentShareBinding;
import com.example.noteapp.ui.gallery.GalleryViewModel;

public class ShareFragment extends Fragment {
    private FragmentShareBinding shareBinding;
    private ShareViewModel shareViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shareViewModel =
                new ViewModelProvider(this).get(ShareViewModel.class);
        shareBinding = FragmentShareBinding.inflate(inflater, container, false);
        View root = shareBinding.getRoot();
        final TextView textView = shareBinding.txtShare;
        shareViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        shareBinding = null;
    }

}
