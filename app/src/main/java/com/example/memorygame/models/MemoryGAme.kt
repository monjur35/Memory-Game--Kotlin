package com.example.memorygame.models

import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGAme (private val boardSizeEnum: BoardSizeEnum){


    val cards:List<MemoryCard>
    var numPairsFound=0
    private var indexOfSingleSelectedCard :Int? =null
    private var numFlips=0

    init {
        val choosenImage= DEFAULT_ICONS.shuffled().take(boardSizeEnum.getNumPAirs())
        val randomizedImages=(choosenImage+choosenImage).shuffled()
         cards=randomizedImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int) : Boolean {
        numFlips++
       val card=cards[position]
        var foundMatch=false

        if (indexOfSingleSelectedCard==null){
            restoreCard()
            indexOfSingleSelectedCard=position
        }

        else{
             foundMatch=checkForMatch(indexOfSingleSelectedCard!!,position)
            indexOfSingleSelectedCard=null
        }

        card.isFaceUp=!card.isFaceUp

        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier){
            return false
        }

        cards[position1].isMatched=true
        cards[position2].isMatched=true
        numPairsFound++
        return true
    }

    private fun restoreCard() {
        for (card :MemoryCard in cards){

            if (!card.isMatched){
                card.isFaceUp=false
            }

        }
    }

    fun haveWon(): Boolean {

        return numPairsFound==boardSizeEnum.getNumPAirs()
    }

    fun isCardFaceUp(position: Int): Boolean {

        return cards[position].isFaceUp
    }

    fun getNumberOfMoves(): Int {

        return numFlips/2
    }
}