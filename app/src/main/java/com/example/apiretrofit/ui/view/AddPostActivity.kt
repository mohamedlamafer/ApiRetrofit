package com.example.apiretrofit.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.apiretrofit.R
import com.example.apiretrofit.ui.viewmodel.PostViewModel

class AddPostActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val etUserId = findViewById<EditText>(R.id.etUserId)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etBody = findViewById<EditText>(R.id.etBody)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnSend.setOnClickListener {
            val userId = etUserId.text.toString().toIntOrNull()
            val title = etTitle.text.toString().trim()
            val body = etBody.text.toString().trim()

            if (userId == null || title.isEmpty() || body.isEmpty()) {
                tvResult.text = "Remplir tous les champs correctement."
                return@setOnClickListener
            }

            tvResult.text = "Envoi en cours..."
            viewModel.createPost(userId, title, body)
        }

        viewModel.createdPost.observe(this) { created ->
            tvResult.text = if (created != null) {
                "Post créé:\nID: ${created.id}\nTitle: ${created.title}\nBody: ${created.body}"
            } else {
                "Erreur: impossible de créer le post."
            }
        }
    }
}
