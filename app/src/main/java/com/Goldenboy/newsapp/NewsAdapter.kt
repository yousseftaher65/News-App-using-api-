package com.Goldenboy.newsapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val activity: Activity , val articles:ArrayList<NewsModel.Articles>?):RecyclerView.Adapter<NewsAdapter.NewsVH>() {
    class NewsVH(view: View):RecyclerView.ViewHolder(view) {

        val titleTv:TextView=view.findViewById(R.id.text)
        val image:ImageView = view.findViewById(R.id.image)
        val parent:CardView = view.findViewById(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val view = activity.layoutInflater
            .inflate(R.layout.list_item,parent,false)
        return NewsVH(view)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.titleTv.text = articles?.get(position)?.title
        Glide.with(activity)
            .load(articles?.get(position)?.urlToImage)
            .into(holder.image)

        holder.parent.setOnClickListener {
            val link = Uri.parse(articles?.get(position)?.url)
            val i = Intent(Intent.ACTION_VIEW,link)
            activity.startActivity(i)
        }
    }

    override fun getItemCount(): Int = articles?.size ?:0
}