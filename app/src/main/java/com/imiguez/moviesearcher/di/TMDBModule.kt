package com.imiguez.moviesearcher.di

import androidx.compose.ui.text.intl.Locale
import com.imiguez.moviesearcher.BuildConfig
import com.imiguez.moviesearcher.ddl.interceptors.ApiKeyInterceptor
import com.imiguez.moviesearcher.ddl.apis.MoviesApi
import com.imiguez.moviesearcher.ddl.interceptors.LanguageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class TMDBModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.TMDB_API_KEY))
            .addInterceptor(LanguageInterceptor(Locale.current.toLanguageTag()))
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TMBD_API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }
}