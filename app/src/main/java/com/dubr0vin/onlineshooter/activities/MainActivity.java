package com.dubr0vin.onlineshooter.activities;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dubr0vin.onlineshooter.R;
import com.dubr0vin.onlineshooter.graphics.Render;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLSurfaceView glSurfaceView = findViewById(R.id.main_surface_view);
        glSurfaceView.setEGLContextClientVersion(2);

        glSurfaceView.setRenderer(new Render());
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}