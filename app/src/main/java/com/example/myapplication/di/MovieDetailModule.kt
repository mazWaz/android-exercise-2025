package com.example.myapplication.di

import com.example.myapplication.common.data.ApiMapper
import com.example.myapplication.movie.data.remote.models.MovieDto
import com.example.myapplication.movie.domain.models.Movie
import com.example.myapplication.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.example.myapplication.movie_detail.data.remote.api.MovieDetailApiService
import com.example.myapplication.movie_detail.data.remote.model.MovieDetailDto
import com.example.myapplication.movie_detail.data.repo_impl.MovieDetailRepositoryImpl
import com.example.myapplication.movie_detail.domain.models.MovieDetail
import com.example.myapplication.movie_detail.domain.repository.MovieDetailRepository
import com.example.myapplication.utils.K
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }


    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        mapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
        movieDetailApiService = movieDetailApiService,
        apiDetailMapper = mapper,
        apiMovieMapper = movieMapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapperImpl()

    @Provides
    @Singleton
    fun provideMovieDetailApiService(): MovieDetailApiService {
        val contentType = "application.json".toMediaType()
        return Retrofit.Builder().baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType)).build()
            .create(MovieDetailApiService::class.java)
    }
}