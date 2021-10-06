package com.dicoding.movieapp.core.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movieapp.R
import com.dicoding.movieapp.core.local.entity.MovieEntity
import com.dicoding.movieapp.core.local.entity.TvShowEntity
import com.dicoding.movieapp.databinding.ItemListMovieBinding
import com.dicoding.movieapp.ui.detail.DetailActivity

class MovieAdapter<RequestType> : RecyclerView.Adapter<MovieAdapter<RequestType>.ListViewHolder>() {

    private var listData = ArrayList<RequestType>()

    fun setData(newListData : List<RequestType>?){
        if (newListData == null) return
            listData.clear()
            listData.addAll(newListData)
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: RequestType){
            when(data){
                is MovieEntity -> {
                    with(binding){
                        tvItemTitle.text = data.original_title
                        tvItemSubtitle.text = data.release_date
                        Glide.with(itemView.context)
                            .load(data.imageBaseUrl + data.backdrop_path)
                            .centerCrop()
                            .into(ivItemImage)

                        itemView.setOnClickListener {
                            val intent = Intent(itemView.context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_ID.toString(), data.id)
                            intent.putExtra(DetailActivity.DETAIL_TYPE, "Movie")
                            itemView.context.startActivity(intent)
                        }
                    }
                }
                is TvShowEntity -> {
                    with(binding){
                        tvItemTitle.text = data.original_name
                        tvItemSubtitle.text = data.first_air_date
                        Glide.with(itemView.context)
                            .load(data.imageBaseUrl + data.backdrop_path)
                            .centerCrop()
                            .into(ivItemImage)

                        itemView.setOnClickListener {
                            val intent = Intent(itemView.context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_ID.toString(), data.id)
                            intent.putExtra(DetailActivity.DETAIL_TYPE, "Tv_Show")
                            itemView.context.startActivity(intent)
                        }
                    }
                }
            }

        }
    }
}