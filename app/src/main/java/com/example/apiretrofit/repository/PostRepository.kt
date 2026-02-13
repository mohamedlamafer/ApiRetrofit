package com.example.apiretrofit.repository

import com.example.apiretrofit.data.model.Comment
import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.service.RetrofitInstance

class PostRepository {

    suspend fun getPosts(): List<Post>? = try {
        RetrofitInstance.api.getPosts()
    } catch (e: Exception) { null }

    suspend fun getPostById(postId: Int): Post? = try {
        RetrofitInstance.api.getPostById(postId)
    } catch (e: Exception) { null }

    suspend fun getCommentsByPostId(postId: Int): List<Comment>? = try {
        RetrofitInstance.api.getCommentsByPostId(postId)
    } catch (e: Exception) { null }

    suspend fun createPost(newPost: Post): Post? = try {
        RetrofitInstance.api.createPost(newPost)
    } catch (e: Exception) { null }
}
