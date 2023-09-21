package com.example.elevenweek

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elevenweek.Utils.getdata
import com.example.elevenweek.data.SearchData
import com.example.elevenweek.databinding.SearchImageBinding

class SaveAdapter(var Context2: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var image = mutableListOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = SearchImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(Context2)
            .load(image[position].url)
            .into((holder as ImageViewHolder).search_image)

        holder.name.text = image[position].title
        holder.like_image.visibility = View.GONE
        holder.dateTime.text = image[position].dateTime
//            getdata(
//            image[position].dateTime,
//                "yyyy-mm-dd'T'HH:mm:ss.SSS+09:00",
//                "yyy-MM-dd HH:mm:ss"
//        )
    }

    inner class ImageViewHolder(binding: SearchImageBinding) : RecyclerView.ViewHolder(binding.root){
        var search_image: ImageView = binding.searchImage
        var like_image : ImageView = binding.like
        var name : TextView = binding.imageName
        var dateTime : TextView = binding.time
        var img_const : ConstraintLayout = binding.searchImgConstraint

        init {
            like_image.visibility = View.GONE
            img_const.setOnClickListener{
                val position = adapterPosition
                (Context2 as MainActivity).removeLikeImage(image[position])
                if(position != RecyclerView.NO_POSITION){
                    image.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }


}