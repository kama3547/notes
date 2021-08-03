package com.example.noteapp.ui.form;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentFormBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.utils.MyApp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private TaskModel taskModel;
    int count = 0;
    final static int REC_AUDIO_REQ_CODE = 5;
    SpeechRecognizer speechRecognizer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT);

            }
        }
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            taskModel = (TaskModel) getArguments().getSerializable("task");
            if (taskModel != null) {
                binding.titleEt.setText(taskModel.getTitle());
            }
        }
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_content_main);
        initClickListener(navController);
        checkPermission();
        initSpeechRecognizer();
    }

    private void initSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext());
        final Intent speechRecord = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecord.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecord.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        binding.voiceBtn.setOnClickListener(v -> {
            if (count == 0) {
                binding.voiceBtn.setImageResource(R.drawable.ic_voice);
                speechRecognizer.startListening(speechRecord);
                count = 1;
            } else {
                binding.voiceBtn.setImageResource(R.drawable.ic_mic_off);
                speechRecognizer.stopListening();
                count = 0;
            }
        });
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> date = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                binding.titleEt.setText(date.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.RECORD_AUDIO}, REC_AUDIO_REQ_CODE);
            }
        }
    }
    private void initClickListener(NavController navController) {
        binding.someId.setOnClickListener(v -> {
            String text = binding.titleEt.getText().toString().trim();
            String title = binding.titleEt.getText().toString();
            if (TextUtils.isEmpty(text)) {
                binding.titleEt.setError("Input text correctly");
            }
            if (taskModel == null) {
                taskModel = new TaskModel(title, "sdfds");
                MyApp.getInstance().noteDao().insert(taskModel);
            } else {
                taskModel.setTitle(title);
                MyApp.getInstance().noteDao().update(taskModel);
            }
            navController.navigateUp();
        });
    }
}