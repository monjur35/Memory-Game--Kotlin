package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.models.BoardSizeEnum
import com.example.memorygame.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var boardSizeEnum :BoardSizeEnum=BoardSizeEnum.HARD



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpRV()

    }



    private fun setUpRV() {

        val choosenImage=DEFAULT_ICONS.shuffled().take(boardSizeEnum.getNumPAirs())
        val randomizedImages=(choosenImage+choosenImage).shuffled()
       binding.rv.adapter=MemoryGameAdapter(this,boardSizeEnum,randomizedImages)
       // binding.rv.layoutManager=StaggeredGridLayoutManager(boardSizeEnum.getWidth(),LinearLayoutManager.VERTICAL)
        binding.rv.layoutManager=GridLayoutManager(this,boardSizeEnum.getWidth())
        binding.rv.setHasFixedSize(true)
    }
}