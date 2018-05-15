package com.movieapp.test.movieapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        Handler(mainLooper).postDelayed({
            var moviePath = Uri.parse("android.resource://" + packageName + "/" + R.raw.glass)
            videoView.setVideoURI(moviePath)

            // setVideoXXXメソッドは非同期なので、リスナーを設定し、準備が完了してから再生するように実装する
            videoView.setOnPreparedListener {
                videoView.start()

                // 再生メニューの表示
                videoView.setMediaController(MediaController(this))
            }

            // 動画が終わったら画面を閉じる
            videoView.setOnCompletionListener {
                finish()
            }
        }, 200)
    }
}
