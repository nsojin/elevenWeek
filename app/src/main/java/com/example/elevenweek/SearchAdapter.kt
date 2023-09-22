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

class SearchAdapter(private  val Context1 : Context) : RecyclerView.Adapter<SearchAdapter.ImageViewHolder>() {

    var image = ArrayList<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = SearchImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = image.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = image[position]

        Glide.with(Context1)
            .load(currentImage.url)
            .into(holder.search_image)

        holder.like_image.visibility =
            if (currentImage.like) {
                View.VISIBLE
            }
            else View.INVISIBLE
        holder.name.text = currentImage.title
        holder.dateTime.text = currentImage.dateTime
//            getdata(
//            currentImage.dateTime,
//            "yyyy-mm-dd'T'HH:mm:ss.SSS+09:00",
//            "yyy-MM-dd HH:mm:ss"
//        )
    }

    inner class ImageViewHolder(binding: SearchImageBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        var search_image: ImageView = binding.searchImage
        var like_image : ImageView = binding.like
        var name : TextView = binding.imageName
        var dateTime : TextView = binding.time
        var img_const : ConstraintLayout = binding.searchImgConstraint

        init {
            like_image.visibility = View.GONE
            search_image.setOnClickListener(this)
            img_const.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val image = image[position]

            image.like = !image.like

            if(image.like){
                (Context1 as MainActivity).addLikedImage(image)
            }
            else {
                (Context1 as MainActivity).removeLikeImage(image)
            }
            notifyItemChanged(position)
            }
        }
    }