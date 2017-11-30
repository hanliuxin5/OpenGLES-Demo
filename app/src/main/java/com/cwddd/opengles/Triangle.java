package com.cwddd.opengles;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by lychee on 17-11-30.
 */

public class Triangle {

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private FloatBuffer vertexBuffer;

    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = {
            // 以逆时针顺序书写
            0.0f, 0.5f, 0.0f, // 顶点
            -1f, 0.0f, 0.0f, // 左下点
            1f, -1f, 0.0f  // 右下点
    };

    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 每个顶点占4字节
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;//多少个顶点
    // 设置颜色
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    private final int mProgram;

    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(
                //每个单精度浮点数占用4字节（32位）
                triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw() {
        GLES20.glUseProgram(mProgram);

        //获取“vPosition”并且 应用
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        //准备要画的图形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        //获取“vColor”并设置
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        //画
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
