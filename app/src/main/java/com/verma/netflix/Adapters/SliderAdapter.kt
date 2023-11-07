package com.verma.netflix.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.verma.netflix.Domain.SliderItems
import com.verma.netflix.R

class SliderAdapter(private val sliderItems: MutableList<SliderItems>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageslider)

        fun setImage(sliderItem: SliderItems) {
            val requestOptions = RequestOptions().transform(CenterCrop(),
                com.bumptech.glide.load.resource.bitmap.RoundedCorners(60)
            )
            Glide.with(itemView.context)
                .load(sliderItem.getImage()) // Assuming SliderItems has a property called imageUrl
                .apply(requestOptions)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.silde_item_container, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val actualPosition = position % sliderItems.size
        holder.setImage(sliderItems[actualPosition])
    }

    override fun getItemCount(): Int {

        return Int.MAX_VALUE
    }

    fun addItems(newItems: List<SliderItems>) {
        sliderItems.addAll(newItems)
        notifyDataSetChanged()
    }
}
