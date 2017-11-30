package com.cwddd.opengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by lychee on 17-11-30.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle triangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //将清除颜色设为黑色
        GLES20.glClearColor(0f, 0f, 0f, 1f);

        triangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置viewport的范围
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //执行清除操作
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        triangle.draw();
    }

    public static int loadShader(int type, String shaderCode) {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
