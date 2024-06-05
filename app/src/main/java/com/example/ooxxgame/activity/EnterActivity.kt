package com.example.ooxxgame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ooxxgame.activity.helper.DatabaseHelper


class EnterActivity: AppCompatActivity() {
    private lateinit var database: SQLiteDatabase
    private lateinit var editTextUsername_login: EditText
    private lateinit var editTextPassword_login: EditText
    private lateinit var buttonLogin: Button
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var textViewRegister: TextView
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //設定APP視窗的大小
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_enter)
        database = DatabaseHelper(this).writableDatabase
        editTextUsername_login.findViewById<EditText>(R.id.edit_enter_account)
        editTextPassword_login.findViewById<EditText>(R.id.edit_enter_password)
        buttonLogin.findViewById<Button>(R.id.bt_enter)
        textViewRegister.findViewById<TextView>(R.id.tv_enroll_intent)

        buttonLogin.setOnClickListener {
            val username = editTextUsername_login.text.toString().trim()
            val password = editTextPassword_login.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "账号或密码为空，请重新输入！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cursor = database.rawQuery(
                "SELECT * FROM ${DatabaseHelper.TABLE_USERS} WHERE ${DatabaseHelper.COLUMN_USERNAME}=? AND ${DatabaseHelper.COLUMN_PASSWORD}=?",
                arrayOf(username, password)
            )

            if (cursor.count > 0) {
                cursor.moveToFirst()
                val userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))

                // Login successful, start main activity
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userId", userId)
                intent.putExtra("username", username)
                startActivity(intent)
                Toast.makeText(this, "欢迎${username}来到OOXX的世界", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show()
            }
        }

        textViewRegister.setOnClickListener {
            val intent = Intent(this, EnrollActivity::class.java)
            startActivity(intent)
        }
    }
}