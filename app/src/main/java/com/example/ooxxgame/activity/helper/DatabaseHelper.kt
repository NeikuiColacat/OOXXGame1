package com.example.ooxxgame.activity.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_db"
        private const val DATABASE_VERSION = 1

        // 用户表
        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"

        // 创建用户表的SQL语句
        private const val CREATE_TABLE_USERS = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 如果需要升级数据库，可以在这里处理
    }
}
