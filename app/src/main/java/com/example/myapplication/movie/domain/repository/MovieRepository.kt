package com.example.myapplication.movie.domain.repository

import com.example.myapplication.movie.domain.models.Movie
import com.example.myapplication.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>

}