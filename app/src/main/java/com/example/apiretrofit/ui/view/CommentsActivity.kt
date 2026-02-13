package com.example.apiretrofit.ui.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.R
import com.example.apiretrofit.data.adapter.CommentAdapter
import com.example.apiretrofit.ui.viewmodel.PostViewModel

class CommentsActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val postId = intent.getIntExtra("postId", -1)

        val tvHeader = findViewById<TextView>(R.id.tvHeader)
        val rvComments = findViewById<RecyclerView>(R.id.rvComments)

        tvHeader.text = if (postId != -1) "Comments for Post ID: $postId" else "Post ID invalide"

        val adapter = CommentAdapter()
        rvComments.layoutManager = LinearLayoutManager(this)
        rvComments.adapter = adapter

        viewModel.comments.observe(this) { comments ->
            adapter.submitList(comments)
        }

        if (postId != -1) {
            viewModel.fetchComments(postId)
        }
    }
}
