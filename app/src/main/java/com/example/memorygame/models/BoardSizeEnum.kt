package com.example.memorygame.models

enum class BoardSizeEnum(val numberCurds:Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);


    fun getWidth() : Int{
        return when(this){
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }

    fun getHeight():Int{
        return numberCurds/getWidth()
    }

    fun getNumPAirs():Int{
        return numberCurds/2
    }

}