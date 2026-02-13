package com.example.apiretrofit.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiretrofit.R
import com.example.apiretrofit.data.adapter.PostAdapter
import com.example.apiretrofit.ui.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPostId = findViewById<EditText>(R.id.etPostId)
        val btnFetchAll = findViewById<Button>(R.id.btnFetchAll)
        val btnFetchById = findViewById<Button>(R.id.btnFetchById)

        val tvSingleId = findViewById<TextView>(R.id.tvSingleId)
        val tvSingleTitle = findViewById<TextView>(R.id.tvSingleTitle)
        val tvSingleBody = findViewById<TextView>(R.id.tvSingleBody)

        val rvPosts = findViewById<RecyclerView>(R.id.rvPosts)

        fun openComments(postId: Int) {
            val i = Intent(this, CommentsActivity::class.java)
            i.putExtra("postId", postId)
            startActivity(i)
        }

        val adapter = PostAdapter { post ->
            val i = Intent(this, CommentsActivity::class.java)
            i.putExtra("postId", post.id)
            i.putExtra("postTitle", post.title)
            startActivity(i)
        }

        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.adapter = adapter

        btnFetchAll.setOnClickListener { viewModel.fetchPosts() }

        btnFetchById.setOnClickListener {
            val id = etPostId.text.toString().toIntOrNull()
            if (id != null) viewModel.fetchPostById(id)
            else {
                tvSingleId.text = "ID invalide"
                tvSingleTitle.text = ""
                tvSingleBody.text = ""
            }
        }
        val btnAddPost = findViewById<Button>(R.id.btnAddPost)
        btnAddPost.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }


        viewModel.posts.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.singlePost.observe(this) { post ->
            if (post == null) {
                tvSingleId.text = "Aucun post trouvé"
                tvSingleTitle.text = ""
                tvSingleBody.text = ""
                tvSingleId.setOnClickListener(null)
            } else {
                tvSingleId.text = "ID: ${post.id} "
                tvSingleTitle.text = post.title
                tvSingleBody.text = post.body

                tvSingleId.setOnClickListener { openComments(post.id) }
            }
        }
    }
}
