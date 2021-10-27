package com.dicoding.movieapp.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movieapp.core.R
import com.dicoding.movieapp.core.databinding.ItemListMovieBinding
import com.dicoding.movieapp.core.domain.model.Movie
import com.dicoding.movieapp.core.domain.model.TvShow

class MovieAdapter<RequestType> : RecyclerView.Adapter<MovieAdapter<RequestType>.ListViewHolder>() {

    private var listData = ArrayList<RequestType>()
    var onItemClick : ((RequestType) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<RequestType>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter<RequestType>.ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter<RequestType>.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: RequestType) {
            when (data) {
                is Movie -> {
                    with(binding) {
                        tvItemTitle.text = data.original_title
                        tvItemSubtitle.text = data.release_date
                        Glide.with(itemView.context)
                            .load(data.imageBaseUrl + data.backdrop_path)
                            .centerCrop()
                            .into(ivItemImage)
                    }
                }
                is TvShow -> {
                    with(binding) {
                        tvItemTitle.text = data.original_name
                        tvItemSubtitle.text = data.first_air_date
                        Glide.with(itemView.context)
                            .load(data.imageBaseUrl + data.backdrop_path)
                            .centerCrop()
                            .into(ivItemImage)
                    }
                }
            }

        }
        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}