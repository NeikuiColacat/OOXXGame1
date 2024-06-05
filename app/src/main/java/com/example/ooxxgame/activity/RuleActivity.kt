package com.example.ooxxgame

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class RuleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //設定APP視窗的大小
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_rule)


    }
}