package com.example.memorygame

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSizeEnum


class MemoryGameAdapter(
    private val context: Context,
    private val boardSizeEnum: BoardSizeEnum,
    private val cardImages: List<Int>
) :
    RecyclerView.Adapter<MemoryGameAdapter.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE=10
        private const val TAG="TAG"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vieww=LayoutInflater.from(context).inflate(R.layout.photo_row,parent,false)

       /* val cardWidth =parent.width-(2* MARGIN_SIZE)
        val cardHeight =parent.height-(4* MARGIN_SIZE)
        val cardSideLength = min(cardWidth,cardHeight)
        val layoutParamss=vieww.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParamss.width=cardSideLength
        layoutParamss.height=cardSideLength
        layoutParamss.setMargins(MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE,MARGIN_SIZE)*/
        return ViewHolder(vieww)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount()=boardSizeEnum.numberCurds

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageBtn=itemView.findViewById<ImageButton>(R.id.imageButton)
        private val card=itemView.findViewById<CardView>(R.id.cardView)


        fun bind(position: Int) {

            imageBtn.setImageResource(cardImages.get(position))

            imageBtn.setOnClickListener{
                Log.i(TAG,"Clicked $position")
                Toast.makeText(context," $position",Toast.LENGTH_SHORT).show()
            }
        }

    }
}