package com.example.apiretrofit.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.R
import com.example.apiretrofit.data.model.Post

class PostAdapter(
    private val onItemClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.VH>() {

    private val items = mutableListOf<Post>()

    fun submitList(data: List<Post>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvId: TextView = v.findViewById(R.id.tvId)
        val tvTitle: TextView = v.findViewById(R.id.tvTitle)
        val tvBody: TextView = v.findViewById(R.id.tvBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val post = items[position]
        holder.tvId.text = "ID: ${post.id}"
        holder.tvTitle.text = post.title
        holder.tvBody.text = post.body

        // ✅ Post كامل clickable
        holder.itemView.setOnClickListener {
            onItemClick(post)
        }
    }

    override fun getItemCount(): Int = items.size
}
