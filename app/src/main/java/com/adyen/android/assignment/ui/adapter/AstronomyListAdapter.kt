package com.adyen.android.assignment.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.api.model.AstronomyPicture
import com.adyen.android.assignment.databinding.ItemPictureBinding


class AstronomyListAdapter(
    private val click: (position: Int) -> Unit
) : RecyclerView.Adapter<AstronomyListHolder>() {

    private var pictureList = mutableListOf<AstronomyPicture>()


    override fun getItemCount(): Int = pictureList.size

    override fun onBindViewHolder(holder: AstronomyListHolder, position: Int) {
        holder.bind(pictureList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstronomyListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return AstronomyListHolder(ItemPictureBinding.bind(view), click)
    }

    //implemented notifyDataSetChanged(),because updating the list after sorting
    fun showListOnUI(pictureLists: List<AstronomyPicture>) {
        this.pictureList = pictureLists.toMutableList()
        notifyDataSetChanged()
    }
}