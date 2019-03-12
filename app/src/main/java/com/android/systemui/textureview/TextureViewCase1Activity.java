package com.android.systemui.textureview;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import com.android.R;
import java.io.IOException;
import static android.view.animation.Animation.REVERSE;

public class TextureViewCase1Activity extends AppCompatActivity {

    TextureView textureView;
    MediaPlayer mediaPlayer;
    SurfaceTexture texture;
    ImageView iv;
    Surface surface;
    public static final String VIDEO_PATH =
            Environment.getExternalStorageDirectory()+"/schoolgirls.mp4";
    private _3DAnimation animation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_view_case1);
        iv = findViewById(R.id.iv);
        textureView = findViewById(R.id.texture);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                playVideo();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

        animation1 = new _3DAnimation(0, 90);
        animation1.initialize(textureView.getWidth(), textureView.getHeight(),
                getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels);
        animation1.setDuration(6000);
        animation1.setRepeatCount(1000);
        animation1.setRepeatMode(REVERSE);
        textureView.setAnimation(animation1);
    }

    /**
     * 播放视频的入口，当SurfaceTexture可得到时被调用
     */
    public void playVideo(){
        Log.d("Test","start... ...");
        if(mediaPlayer==null){
            texture = textureView.getSurfaceTexture();
            surface = new Surface(texture);
            initMediaPlayer();
        }
    }

    /**
     * 初始化媒体播放器
     */
    private void initMediaPlayer() {
        this.mediaPlayer = new MediaPlayer();
        try {
            Log.d("Test","start1... ...");
            mediaPlayer.setDataSource(VIDEO_PATH);//视频源
            mediaPlayer.setSurface(surface);//设置窗口
            mediaPlayer.prepareAsync();//异步地准备播放器来回放
            mediaPlayer.setOnPreparedListener(mp -> {
                try {
                    if (mp != null) {
                        mp.start(); //视频开始播放
                        animation1.start();
                        Log.d("Test","play... ...");
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    Log.d("Test","start2... ..."+e.getMessage());
                }
            });//准备监听
            mediaPlayer.setLooping(true);//循环播放
            Log.d("Test","start3... ...");
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Log.d("Test","start4... ..."+ e1.getMessage());
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Log.d("Test","start5... ..."+ e1.getMessage());
        } catch (IllegalStateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Log.d("Test","start6... ..."+ e1.getMessage());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Log.d("Test","start7... ..."+ e1.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (textureView.isAvailable()) {
            playVideo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (texture != null) {
            texture.release();  //停止视频的绘制线程
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer =null;
        }
    }

    /**
     * 截图
     * @param view
     */
    public void screen(View view) {
        iv.setImageBitmap(textureView.getBitmap());
    }

}