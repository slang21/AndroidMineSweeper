package com.spencer.minesweeper.View

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.spencer.minesweeper.Model.MinesweeperModel
import com.spencer.minesweeper.R


class MinesweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackgroud : Paint = Paint()
    var paintLine: Paint = Paint()
    var paintO : Paint = Paint()
    var finished : Boolean = false

    init {
        paintBackgroud.color = Color.WHITE
        paintBackgroud.style = Paint.Style.FILL

        paintLine.color = Color.BLACK
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 7f


        paintO.color = Color.rgb(126, 47, 60)
        paintO.style = Paint.Style.STROKE
        paintO.textSize = 70f
        paintO.strokeWidth = 7f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackgroud)

        drawBoard(canvas)

        drawPlayers(canvas)
    }

    private fun drawBoard(canvas: Canvas?) {
        // border
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)
        // four horizontal lines
        canvas?.drawLine(0f, (height / 5).toFloat(), width.toFloat(), (height / 5).toFloat(),
            paintLine)
        canvas?.drawLine(0f, (2 * height / 5).toFloat(), width.toFloat(), (2 * height / 5).toFloat(),
            paintLine)
        canvas?.drawLine(0f, (3 * height / 5).toFloat(), width.toFloat(), (3 * height / 5).toFloat(),
            paintLine)
        canvas?.drawLine(0f, (4 * height / 5).toFloat(), width.toFloat(), (4 * height / 5).toFloat(), paintLine)

        // four vertical lines
        canvas?.drawLine((width / 5).toFloat(), 0f, (width / 5).toFloat(), height.toFloat(),
            paintLine)
        canvas?.drawLine((2 * width / 5).toFloat(), 0f, (2 * width / 5).toFloat(), height.toFloat(),
            paintLine)
        canvas?.drawLine((3 * width / 5).toFloat(), 0f, (3 * width / 5).toFloat(), height.toFloat(),
            paintLine)
        canvas?.drawLine((4 * width / 5).toFloat(), 0f, (4 * width / 5).toFloat(), height.toFloat(),
            paintLine)

    }

    private fun drawPlayers(canvas: Canvas?){
        for (i in 0..4) {
            for (j in 0..4) {
                if (MinesweeperModel.getViewContent(i, j) != -1) {
                    if(MinesweeperModel.getFieldContent(i,j) == MinesweeperModel.BOMB && MinesweeperModel.getViewContent(i, j) != 50) {
                        finished = true
                        Toast.makeText(context, context.getString(R.string.Lose_Message), Toast.LENGTH_SHORT).show()
                    } else if(MinesweeperModel.getFieldContent(i,j) != MinesweeperModel.BOMB && MinesweeperModel.getViewContent(i, j) == 50) {
                        finished = true
                        Toast.makeText(context, context.getString(R.string.Lose_Message), Toast.LENGTH_SHORT).show()
                    }
                    val centerX = (i * width / 5 + width / 10).toFloat()
                    val centerY = (j * height / 5 + height / 10).toFloat()

                    canvas?.drawText(MinesweeperModel.getViewContent(i, j).toString(), centerX, centerY, paintO)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(!finished) {
            if (event?.action == MotionEvent.ACTION_DOWN) {
                val tX = event.x.toInt() / (width / 5)
                val tY = event.y.toInt() / (height / 5)


                if (tX < 5 && tY < 5 && MinesweeperModel.getViewContent(tX, tY) == -1) {
                    if (MinesweeperModel.getFlagOn()) {
                        MinesweeperModel.flagSquare(tX, tY)
                    } else {
                        MinesweeperModel.trySquare(tX, tY)
                    }
                    invalidate()
                }

                if(MinesweeperModel.gameOver()) {
                    Toast.makeText(context, context.getString(R.string.Win_Message), Toast.LENGTH_SHORT).show()
                }
            }
        }

        return true
    }

    fun flagToggle() {
        MinesweeperModel.toggleFlagOn()
    }

    fun clearBoard() {
        MinesweeperModel.resetModel()
        finished = false
        invalidate()
    }
}