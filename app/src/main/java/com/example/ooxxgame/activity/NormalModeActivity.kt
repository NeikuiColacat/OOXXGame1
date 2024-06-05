package com.example.ooxxgame


import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NormalModeActivity : AppCompatActivity() {
    private val gridSize = 5 // 网格大小
    private val gridButtons = Array(gridSize) { arrayOfNulls<Button>(gridSize) } // 格子按钮数组
    private var currentPlayer = "X" // 当前玩家
    private var gameEnded = false // 游戏是否结束

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置APP视窗的大小
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_normalmode)

        // 初始化格子按钮数组
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                val buttonId = resources.getIdentifier("button$i$j", "id", packageName)
                gridButtons[i][j] = findViewById(buttonId)
            }
        }
    }

    /**
     * 处理格子按钮的点击事件
     */
    fun onGridButtonClick(view: View) {
        if (gameEnded) return // 如果游戏已经结束，则不再处理按钮点击事件

        val button = view as Button
        val tag = button.tag.toString()
        val row = tag[0].toString().toInt()
        val col = tag[1].toString().toInt()

        if (button.text.isNotEmpty()) {
            Toast.makeText(this, "该位置已经有棋子了！", Toast.LENGTH_SHORT).show()
            return
        }

        button.text = currentPlayer

        // 检查当前行和列中是否有超过两个连续的棋子
        if (checkRowAndColumn(row, col)) {
            Toast.makeText(this, "同一行或列不能有超过两个连续的棋子！", Toast.LENGTH_SHORT).show()
            button.text = ""
            return
        }

        // 检查当前行和列中的棋子数量是否相同
        if (!checkRowCountAndColumnCount()) {
            Toast.makeText(this, "每行和每列的棋子数量必须相同！", Toast.LENGTH_SHORT).show()
            button.text = ""
            return
        }

        // 切换玩家
        currentPlayer = if (currentPlayer == "X") "O" else "X"

        // 检查游戏是否结束
        if (checkGameEnd()) {
            gameEnded = true
            Toast.makeText(this, "游戏结束！", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 检查当前行和列中是否有超过两个连续的棋子
     */
    private fun checkRowAndColumn(row: Int, col: Int): Boolean {
        var count = 0
        for (i in 0 until gridSize) {
            if (gridButtons[row][i]?.text == currentPlayer) {
                count++
                if (count > 2) return true
            } else {
                count = 0
            }
        }

        count = 0
        for (i in 0 until gridSize) {
            if (gridButtons[i][col]?.text == currentPlayer) {
                count++
                if (count > 2) return true
            } else {
                count = 0
            }
        }

        return false
    }

    /**
     * 检查每行和每列中的棋子数量是否相同
     */
    private fun checkRowCountAndColumnCount(): Boolean {
        for (i in 0 until gridSize) {
            var rowCount = 0
            var colCount = 0
            for (j in 0 until gridSize) {
                if (gridButtons[i][j]?.text == "X" || gridButtons[i][j]?.text == "O") {
                    rowCount++
                }
                if (gridButtons[j][i]?.text == "X" || gridButtons[j][i]?.text == "O") {
                    colCount++
                }
            }
            if (rowCount != colCount || rowCount > gridSize / 2) {
                return false
            }
        }
        return true
    }

    /**
     * 检查游戏是否结束
     */
    private fun checkGameEnd(): Boolean {
        // 检查每行和每列中是否都有相同数量的棋子
        for (i in 0 until gridSize) {
            var rowCount = 0
            var colCount = 0
            for (j in 0 until gridSize) {
                if (gridButtons[i][j]?.text == "X" || gridButtons[i][j]?.text == "O") {
                    rowCount++
                }
                if (gridButtons[j][i]?.text == "X" || gridButtons[j][i]?.text == "O") {
                    colCount++
                }
            }
            if (rowCount != gridSize / 2 || colCount != gridSize / 2) {
                return false
            }
        }

        // 检查对角线上是否有相同数量的棋子
        var diagonalCount1 = 0
        var diagonalCount2 = 0
        for (i in 0 until gridSize) {
            if (gridButtons[i][i]?.text == "X" || gridButtons[i][i]?.text == "O") {
                diagonalCount1++
            }
            if (gridButtons[i][gridSize - 1 - i]?.text == "X" || gridButtons[i][gridSize - 1 - i]?.text == "O") {
                diagonalCount2++
            }
        }
        if (diagonalCount1 != gridSize / 2 || diagonalCount2 != gridSize / 2) {
            return false
        }

        return true
    }
}
