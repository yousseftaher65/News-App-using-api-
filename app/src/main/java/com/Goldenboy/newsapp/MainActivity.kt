package com.Goldenboy.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.zip.Inflater


class MainActivity : AppCompatActivity() {
    lateinit var refresh :SwipeRefreshLayout
     var  retrofit =Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNews()

        refresh = findViewById(R.id.refresh)
        refresh.setOnRefreshListener { loadNews() }

        val mAdView:AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    fun loadNews(){
        val retrofit =Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val callable = retrofit.create(CallableInterface::class.java)
        val newsCall = callable.getGeneralNews()

        newsCall.enqueue( object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val article = news?.articles

                val adapter = NewsAdapter(this@MainActivity,article)

                val rv :RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter

                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_list,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.general -> refresh.setOnRefreshListener { generalNews() }
            R.id.sports -> refresh.setOnRefreshListener { sportsNews() }
            R.id.entertainment -> refresh.setOnRefreshListener { entertainmentNews() }
            R.id.technology -> refresh.setOnRefreshListener { generalNews() }
        }
        when(id){
            R.id.general -> generalNews()
            R.id.sports -> sportsNews()
            R.id.entertainment -> entertainmentNews()
            R.id.technology -> technology()
        }

        return super.onOptionsItemSelected(item)
    }
    fun generalNews(){
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getGeneralNews().enqueue( object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val article = news?.articles

                val adapter = NewsAdapter(this@MainActivity,article)

                val rv :RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter

                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("trace","Error${t.localizedMessage}")

            }
        })
    }
    fun sportsNews(){
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getSportsNews().enqueue( object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val article = news?.articles

                val adapter = NewsAdapter(this@MainActivity,article)

                val rv :RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter

                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("trace","Error${t.localizedMessage}")

            }
        })
    }
    fun entertainmentNews(){
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getEntertainmentNews().enqueue( object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val article = news?.articles

                val adapter = NewsAdapter(this@MainActivity,article)

                val rv :RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter

                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("trace","Error${t.localizedMessage}")

            }
        })
    }
    fun technology(){
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getTechnologyNews().enqueue( object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val article = news?.articles

                val adapter = NewsAdapter(this@MainActivity,article)

                val rv :RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter

                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("trace","Error${t.localizedMessage}")

            }
        })
    }
}