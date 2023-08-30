package com.annaginagili.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getPosts()
    }

    private fun getPosts() {
        val call = RetrofitClient.getInstance().getApi().getPosts()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val postList: ArrayList<Post> = response.body() as ArrayList<Post>
                val titleList = ArrayList<String>()
                val idList = ArrayList<Int>()
                val bodyList = ArrayList<String>()
                for (i in postList) {
                    titleList.add(i.title)
                    bodyList.add(i.body)
                    idList.add(i.id)
                }
                adapter = Adapter(this@MainActivity, Item.getData(titleList, bodyList, idList))
                recycler.adapter = adapter
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
            }
        })
    }
}