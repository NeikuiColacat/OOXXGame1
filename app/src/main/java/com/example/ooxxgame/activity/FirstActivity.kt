package com.example.ooxxgame


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ooxxgame.activity.helper.DatabaseHelper

class FirstActivity : AppCompatActivity() {
    private val timeOut = 2000
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 設置 APP 視窗的大小
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_first)

        dbHelper = DatabaseHelper(this)

        val bt: Button = findViewById(R.id.play_bt)
       bt.setOnClickListener {
//            if (isUserLoggedIn()) {
//                // If user is logged in, start main activity
//                startActivity(Intent(this@FirstActivity, MainActivity::class.java))
//            } else {
//                // If user is not logged in, start login activity
//                startActivity(Intent(this@FirstActivity, EnterActivity::class.java))
//            }
                 startActivity(Intent(this@FirstActivity, GameActivity::class.java))
        }
    }

//    private fun isUserLoggedIn(): Boolean {
//
//    }
}