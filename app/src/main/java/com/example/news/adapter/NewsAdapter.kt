package com.example.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.models.Data

class NewsAdapter(private val dataList: List<Data>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val author: TextView = itemView.findViewById(R.id.txt_author)
        val content: TextView = itemView.findViewById(R.id.txt_content)
        val title: TextView = itemView.findViewById(R.id.txt_title)
        val date: TextView = itemView.findViewById(R.id.txt_date)
        val time: TextView = itemView.findViewById(R.id.txt_time)
        val imageView: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.author.text = data.author
        holder.content.text = data.content
        holder.title.text = data.title
        holder.date.text = data.date
        holder.time.text = data.time
    }
}