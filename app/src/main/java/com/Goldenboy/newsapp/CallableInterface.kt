package com.Goldenboy.newsapp

import retrofit2.Call
import retrofit2.http.GET

interface CallableInterface {
    @GET ("/v2/top-headlines?country=eg&category=sports&apiKey=3c5ed2223f90423aa4df21aa3421b5e2")
    fun getSportsNews():Call<NewsModel>

    @GET ("/v2/top-headlines?country=eg&category=general&apiKey=3c5ed2223f90423aa4df21aa3421b5e2")
    fun getGeneralNews():Call<NewsModel>

    @GET ("/v2/top-headlines?country=eg&category=entertainment&apiKey=3c5ed2223f90423aa4df21aa3421b5e2")
    fun getEntertainmentNews():Call<NewsModel>

    @GET ("/v2/top-headlines?country=eg&category=technology&apiKey=3c5ed2223f90423aa4df21aa3421b5e2")
    fun getTechnologyNews():Call<NewsModel>




}