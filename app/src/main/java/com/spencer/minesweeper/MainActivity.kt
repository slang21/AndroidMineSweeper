package com.spencer.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spencer.minesweeper.View.MinesweeperView
import kotlinx.android.synthetic.main.activity_main.*

//

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearButton.setOnClickListener {
            minView.clearBoard()
        }

        flagToggle.setOnClickListener{
            minView.flagToggle()
        }
    }
}
