package com.cwddd.opengles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView=new MyGLSurfaceView(this);

        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }


    class MyGLSurfaceView extends GLSurfaceView {

        private MyGLRenderer renderer;

        public MyGLSurfaceView(Context context) {
            super(context);

            //设置版本
            setEGLContextClientVersion(2);
            //实例化renderer
            renderer = new MyGLRenderer();
            //设置着色器
            setRenderer(renderer);
            //设置着色模式
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
    }
}
