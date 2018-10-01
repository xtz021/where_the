package com.example.oxygen.test_video;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnPaly;
    VideoView videoPlayer;
    MediaController mediaC;
    String videoPath;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPaly = (Button) findViewById(R.id.btnPlay);
        videoPlayer = (VideoView) findViewById(R.id.videoView);
        mediaC  = new MyMediaController(this);
        mediaC.show(0);

    }

    public void ViewPlay(View view)
    {
            videoPath = "android.resource://com.example.oxygen.test_video/"+R.raw.test1;
            uri = Uri.parse(videoPath);
            videoPlayer.setVideoURI(uri);
            videoPlayer.setMediaController(mediaC);
            videoPlayer.start();
    }

    class MyMediaController extends MediaController
    {

        public MyMediaController(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        public MyMediaController(Context context, boolean useFastForward) {
            super(context, useFastForward);
        }

        public MyMediaController(Context context) {
            super(context);
        }

        @Override
        public void show(int timeout) {
            super.show(0);
        }
    }
}
