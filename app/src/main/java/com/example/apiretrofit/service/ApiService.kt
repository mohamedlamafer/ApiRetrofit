package com.example.apiretrofit.service

import com.example.apiretrofit.data.model.Comment
import com.example.apiretrofit.data.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Post

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") postId: Int): List<Comment>

    @POST("posts")
    suspend fun createPost(@Body newPost: Post): Post
}
