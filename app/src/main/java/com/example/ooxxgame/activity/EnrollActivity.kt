package com.example.ooxxgame

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.ooxxgame.activity.helper.DatabaseHelper.Companion.COLUMN_PASSWORD
import com.example.ooxxgame.activity.helper.DatabaseHelper.Companion.COLUMN_USERNAME
import com.example.ooxxgame.activity.helper.DatabaseHelper.Companion.TABLE_USERS

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ooxxgame.activity.helper.DatabaseHelper

class EnrollActivity:AppCompatActivity() {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var buttonRegister: Button
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //設定APP視窗的大小
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.actvity_enroll)
        inite()
        // 实例化DatabaseHelper类并获取可写的数据库对象
        dbHelper = DatabaseHelper(this)
        database = dbHelper.writableDatabase

        // 设置注册按钮点击事件
        buttonRegister.setOnClickListener {
            registerUser()
        }

    }

     fun inite() {
         // 初始化UI元素
         editTextUsername = findViewById(R.id.edit_enroll_account)
         editTextPassword = findViewById(R.id.edit_enroll_password)
         editTextPassword2 = findViewById(R.id.edit_enroll_password2)
         buttonRegister = findViewById(R.id.bt_enroll)
    }
    private fun registerUser() {
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val password2 = editTextPassword2.text.toString().trim()

        // 检查用户名和密码是否为空
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "账号或密码为空，请重新输入！！", Toast.LENGTH_SHORT).show()
            return
        }

        // 检查两次输入密码是否相同
        if (password != password2.trim()) {
            Toast.makeText(this, "两次输入不同，请确认密码！", Toast.LENGTH_SHORT).show()
            return
        }

        // 检查用户名是否已存在
        val cursor = database.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME=?",
            arrayOf(username)
        )
        if (cursor.count > 0) {
            Toast.makeText(this, "这个用户名已经存在啦！", Toast.LENGTH_SHORT).show()
            cursor.close() // Close the cursor
            return
        }

        // 插入用户信息到数据库
        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val result = database.insert(TABLE_USERS, null, contentValues)

        if (result != -1L) {
            Toast.makeText(this, "恭喜${username}注册成功", Toast.LENGTH_SHORT).show()
            // 注册成功后可以进行其他操作，例如跳转到登录页面
        } else {
            Toast.makeText(this, "很遗憾，${username}注册失败请检查", Toast.LENGTH_SHORT).show()
            Log.e("Registration", "Failed to register user: $username")
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }



}