package com.example.apiretrofit.ui.viewmodel

import androidx.lifecycle.*
import com.example.apiretrofit.data.model.Comment
import com.example.apiretrofit.data.model.Post
import com.example.apiretrofit.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repo = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _singlePost = MutableLiveData<Post?>()
    val singlePost: LiveData<Post?> = _singlePost

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private val _createdPost = MutableLiveData<Post?>()
    val createdPost: LiveData<Post?> = _createdPost

    fun fetchPosts() {
        viewModelScope.launch { _posts.value = repo.getPosts() ?: emptyList() }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch { _singlePost.value = repo.getPostById(postId) }
    }

    fun fetchComments(postId: Int) {
        viewModelScope.launch { _comments.value = repo.getCommentsByPostId(postId) ?: emptyList() }
    }

    fun createPost(userId: Int, title: String, body: String) {
        viewModelScope.launch {
            val newPost = Post(userId = userId, id = 0, title = title, body = body)
            _createdPost.value = repo.createPost(newPost)
        }
    }
}
