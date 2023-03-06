package com.vladimir_tsurko.ecommerse.presentation.adapters.colorsAdapter

import android.graphics.Color
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.vladimir_tsurko.ecommerse.databinding.ColorListItemBinding
import com.vladimir_tsurko.ecommerse.domain.models.ColorModel



class ColorsAdapter() : ListAdapter<ColorModel, ColorsViewHolder>(ColorsDiffUtilCallback) {

    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val binding = ColorListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ColorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.color.setCardBackgroundColor(Color.parseColor(item.color))
        holder.binding.root.setOnClickListener{
            onItemClickListener?.invoke(item.id)
        }
        if(item.isSelected){
            holder.binding.colorContainer.visibility = View.VISIBLE
        } else {
            holder.binding.colorContainer.visibility = View.INVISIBLE
        }



    }


}