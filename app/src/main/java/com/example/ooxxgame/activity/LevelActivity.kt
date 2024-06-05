package com.example.ooxxgame.activity

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.ooxxgame.R
import com.example.ooxxgame.activity.adapter.LevelAdapter

class LevelActivity :AppCompatActivity(){
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        gridView = findViewById(R.id.gridView)

        val gridAdapter = LevelAdapter(this)
        gridView.adapter = gridAdapter
    }
}