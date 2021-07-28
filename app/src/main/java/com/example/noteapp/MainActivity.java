package com.example.noteapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.noteapp.utils.PrefrencesHelper;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST =1 ;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    PrefrencesHelper prefrencesHelper = new PrefrencesHelper();

    SharedPreferences sharedPreferences;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbarMain);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        NavigationView navigationView1 = findViewById(R.id.nav_view);
        View view = navigationView1.getHeaderView(0);
        imageView = view.findViewById(R.id.image_save);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        prefrencesHelper.init(this);
        if (!prefrencesHelper.isShown()) {
            navController.navigate(R.id.onBoardFragment);
        }
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        destination(navController);
        saveToGallery();
        binding.appBarMain.fab.setOnClickListener(v -> {
            navController.navigate(R.id.action_nav_home_to_formFragment);
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(imageView.getContext()).create();
                alertDialog.setTitle("dsfsdf");
                alertDialog.setMessage("dsfsdf");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", (dialog, which) -> {

                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", (dialog, which) -> {
                    prefrencesHelper.OnsaveImageDefault();

                    Glide.with(MainActivity.this)
                            .load(mGetContent)
                            .circleCrop()
                            .placeholder(R.drawable.ic_baseline_person_pin_24)
                            .into(imageView);

                });
                alertDialog.show();
                return false;
            }
        });
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    try {
                        final InputStream imageStream = getContentResolver().openInputStream(uri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                        sharedPreferences.edit().putString("saveimage", encodedImage).commit();
                        prefrencesHelper.onSaveImage();
                        Glide.with(MainActivity.this)
                                .load(uri)
                                .circleCrop()
                                .into(imageView);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

    private void saveToGallery() {
        sharedPreferences = getSharedPreferences("", MODE_PRIVATE);
        if (prefrencesHelper.IsshwonImage()) {
            if (!sharedPreferences.getString("saveimage", "").equals("")) {
                byte[] decodedString = Base64.decode(sharedPreferences.getString("saveimage", ""), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
            }
        }
    }
    private void destination(NavController navController){
        navController.addOnDestinationChangedListener((controller, destination, arguments) ->{
            if (destination.getId() == R.id.formFragment ||  destination.getId() == R.id.onBoardFragment){
                binding.appBarMain.toolbarMain.setVisibility(View.GONE);
                binding.appBarMain.fab.setVisibility(View.GONE);
            }else {
                binding.appBarMain.toolbarMain.setVisibility(View.VISIBLE);
                binding.appBarMain.fab.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}