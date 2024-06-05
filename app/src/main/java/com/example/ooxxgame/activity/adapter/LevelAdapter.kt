package com.example.ooxxgame.activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.ooxxgame.GameActivity
import com.example.ooxxgame.R

class LevelAdapter(private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        // 返回网格项的数量
        return 30
    }

    override fun getItem(position: Int): Any {
        // 返回指定位置的项
        return position
    }

    override fun getItemId(position: Int): Long {
        // 返回指定位置的项的ID
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            // 如果没有可重用的视图项，则创建一个新的视图项
            view = LayoutInflater.from(context).inflate(R.layout.level_item, parent, false)

            // 创建ViewHolder并将视图项的子视图保存在其中
            viewHolder = ViewHolder()
            viewHolder.textView = view.findViewById(R.id.level_rl_tv)

            // 将ViewHolder与视图项关联
            view.tag = viewHolder
        } else {
            // 如果有可重用的视图项，则重用它
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // 设置网格项的内容
        viewHolder.textView.text = "${position + 1}"

        // 为视图项设置点击事件监听器
        // 为视图项设置点击事件监听器
        view.setOnClickListener {
            // 创建跳转到目标界面的意图
            val intent = Intent(context, GameActivity::class.java)
            // 在这里可以设置传递给目标界面的额外数据，例如：
            // intent.putExtra("key", value)

            // 启动意图，进行界面跳转
            context.startActivity(intent)
        }

        return view
    }

    // ViewHolder用于保存视图项的子视图
    private class ViewHolder {
        lateinit var textView: TextView
    }
}