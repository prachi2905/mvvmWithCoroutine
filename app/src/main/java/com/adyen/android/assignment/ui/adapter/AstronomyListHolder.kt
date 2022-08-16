package com.adyen.android.assignment.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.api.model.AstronomyPicture
import com.adyen.android.assignment.databinding.ItemPictureBinding
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter


class AstronomyListHolder(
    private val binding: ItemPictureBinding,
    private val click: (position: Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(listItem: AstronomyPicture) = with(binding) {
        val picasso = Picasso.get()
        imageView.apply {
            picasso.load(listItem.url).placeholder(R.drawable.ic_placeholder)
                .into(imageView)

        }

        title.text = listItem.title
        val formattedDate: String = listItem.date.format(DateTimeFormatter.ofPattern("dd/MM/y"))
        dateTime.text = formattedDate

        root.setOnClickListener {
            click(layoutPosition)
        }
    }
}