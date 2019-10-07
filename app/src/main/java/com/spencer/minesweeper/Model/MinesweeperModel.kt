package com.spencer.minesweeper.Model

import android.icu.lang.UCharacter.DecompositionType.CIRCLE

object MinesweeperModel {

    public val BOMB : Int = 100
    private val model = arrayOf(
        intArrayOf(0, 1, BOMB, 1, 0),
        intArrayOf(0, 1, 1, 1, 0),
        intArrayOf(0, 0, 1, 1, 1),
        intArrayOf(0, 0, 2, BOMB, 2),
        intArrayOf(0, 0, 2, BOMB, 2)
    )

    private val viewModel = arrayOf(
        intArrayOf(-1, -1, -1, -1, -1),
        intArrayOf(-1, -1, -1, -1, -1),
        intArrayOf(-1, -1, -1, -1, -1),
        intArrayOf(-1, -1, -1, -1, -1),
        intArrayOf(-1, -1, -1, -1, -1)
    )

    private var flags = arrayOf(intArrayOf())

    private var bombArray = arrayOf(
        intArrayOf(0,0),
        intArrayOf(1,1),
        intArrayOf(2,2)
    )

    private var flagOn : Boolean = false

    fun getFieldContent(x:Int, y:Int) = model[x][y]

    fun setModelContent(x: Int, y : Int, state: Int) { // would allow me to set bombs etc in actual model
        model[x][y] = state
    }

    fun getViewContent(x:Int, y:Int) = viewModel[x][y]

    fun trySquare(x: Int, y : Int) {
        viewModel[x][y] = model[x][y]
    }

    fun flagSquare(x: Int, y : Int) {
        viewModel[x][y] = 50
    }

    fun getFlagOn() = flagOn

    fun toggleFlagOn() {
        flagOn = !flagOn
    }

    fun gameOver() : Boolean {
        for(i in 0..4) {
            for (j in 0..4) {
                if(viewModel[i][j] == -1) {
                    return false
                }
            }
        }
        return true
    }


    fun resetModel() {
        for(i in 0..4) {
            for (j in 0..4) {
                viewModel[i][j] = -1
            }
        }
    }
}