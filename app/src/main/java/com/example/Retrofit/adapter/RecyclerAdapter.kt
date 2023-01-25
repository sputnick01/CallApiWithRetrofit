package com.example.Retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.Retrofit.R
import com.example.Retrofit.model.Movie

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    var movieList: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.movieName.text = movieList.get(position).title
        holder.movieType.text = movieList.get(position).type
        holder.movieYear.text = movieList.get(position).year
        Glide.with(context).load(movieList.get(position).poster)
            .placeholder(R.drawable.placeholder_movie)
            .apply(RequestOptions().centerCrop()).into(holder.movieImage)


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieListItem(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val movieName = itemView!!.findViewById<TextView>(R.id.textName_move)
        val movieImage = itemView!!.findViewById<ImageView>(R.id.imageview_move)
        val movieType = itemView!!.findViewById<TextView>(R.id.textType_move)
        val movieYear = itemView!!.findViewById<TextView>(R.id.textYear_move)

    }


}