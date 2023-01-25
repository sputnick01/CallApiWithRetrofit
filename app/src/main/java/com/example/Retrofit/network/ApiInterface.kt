package com.example.Retrofit.network

import com.example.Retrofit.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("?apikey=c06e4ff")
    fun getMovies(@Query("s") query: String): Call<MovieResponse>

    companion object {

        var BASE_URL = "http://omdbapi.com/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}