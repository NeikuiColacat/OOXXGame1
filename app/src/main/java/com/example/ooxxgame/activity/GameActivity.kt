package com.example.ooxxgame

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.ooxxgame.activity.LevelActivity

class GameActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //設定APP視窗的大小
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_game)
        val rl_rule:RelativeLayout
        val rl_normal:RelativeLayout

        rl_rule=findViewById(R.id.rl_rule)
        rl_normal=findViewById(R.id.rl_normalmode)
        rl_rule.setOnClickListener{
            val intent=Intent(this@GameActivity, RuleActivity::class.java)
            startActivity(intent)
        }
        rl_normal.setOnClickListener {
            val intent=Intent(this@GameActivity, LevelActivity::class.java)
            startActivity(intent)
        }


    }
}