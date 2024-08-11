package com.sosial.bantuanku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SliderAdapter(homeFragment: HomeFragment) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val slides = listOf(
        SlideItem(R.drawable.program1, R.string.programke1),
        SlideItem(R.drawable.program2, R.string.programke2),
        SlideItem(R.drawable.program3, R.string.programke3),
        SlideItem(R.drawable.program4, R.string.programke4)

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val slide = slides[position]
        holder.imageView.setImageResource(slide.imageResId)
        holder.textView.setText(slide.textResId)
    }

    override fun getItemCount(): Int {
        return slides.size
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    data class SlideItem(val imageResId: Int, val textResId: Int)
}
