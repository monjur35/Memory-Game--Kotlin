package com.example.memorygame

import android.animation.ArgbEvaluator
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.models.BoardSizeEnum
import com.example.memorygame.models.MemoryCard
import com.example.memorygame.models.MemoryGAme
import com.example.memorygame.utils.DEFAULT_ICONS
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MemoryGameAdapter
    private lateinit var binding: ActivityMainBinding
    private var boardSizeEnum: BoardSizeEnum = BoardSizeEnum.EASY
    private lateinit var memoryGAme: MemoryGAme


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpRV()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh_menu->{
                if (memoryGAme.getNumberOfMoves()> 0 && !memoryGAme.haveWon()){

                    showAlertDialog("Quit your current game ?",null,View.OnClickListener {
                        setUpRV()
                        //Toast.makeText(this,"sdioas",Toast.LENGTH_SHORT).show()

                    })
                }
                else{
                    setUpRV()
                }

            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun showAlertDialog(title: String ,view :View?,positiveButtonClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
                AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancel",null)
            .setPositiveButton("Ok"){_,_ ->
                positiveButtonClickListener.onClick(null)

            }.show()
    }

    private fun setUpRV() {
        memoryGAme = MemoryGAme(boardSizeEnum)
        adapter = MemoryGameAdapter(
            this,
            boardSizeEnum,
            memoryGAme.cards,
            object : MemoryGameAdapter.CardClickListener {
                override fun onCardClicklistener(position: Int) {
                    updateGamewithFlip(position)
                }

            })

        binding.rv.adapter=adapter
        // binding.rv.layoutManager=StaggeredGridLayoutManager(boardSizeEnum.getWidth(),LinearLayoutManager.VERTICAL)
        binding.rv.layoutManager = GridLayoutManager(this, boardSizeEnum.getWidth())
        binding.rv.setHasFixedSize(true)
        //mmmm
    }

    private fun updateGamewithFlip(position: Int) {
        if (memoryGAme.haveWon()){
            Snackbar.make(binding.rooooot,"You have won",Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGAme.isCardFaceUp(position)){
            Snackbar.make(binding.rooooot,"Invalid move",Snackbar.LENGTH_SHORT).show()
            return
        }

        if (memoryGAme.flipCard(position)){
            Log.e("TAG", "match found : ${memoryGAme.numPairsFound}", )

            binding.points.text="Pairs ${memoryGAme.numPairsFound} / ${boardSizeEnum.getNumPAirs()}"
            val color=ArgbEvaluator().evaluate(
                memoryGAme.numPairsFound.toFloat()/boardSizeEnum.getNumPAirs(),
                ContextCompat.getColor(this,R.color.progress_none),
                ContextCompat.getColor(this,R.color.progress_full)
            ) as Int

            binding.points.setTextColor(color)

            if (memoryGAme.haveWon()){
                Snackbar.make(binding.rooooot,"You won!! Congratulations",Snackbar.LENGTH_LONG).show()
            }
        }

        binding.numberOfMoves.text="Moves : ${memoryGAme.getNumberOfMoves()}"

        adapter.notifyDataSetChanged()

    }
}