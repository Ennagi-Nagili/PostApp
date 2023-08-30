package com.annaginagili.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneByOne : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var body: TextView
    lateinit var recycler: RecyclerView
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_by_one)
        title = findViewById(R.id.title)
        body = findViewById(R.id.body)
        recycler = findViewById(R.id.recycler)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getPostById()
        getComments()
    }

    private fun getPostById() {
        val call = RetrofitClient.getInstance().getApi().getPostById(intent.getIntExtra("id", 0))
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                title.text = response.body()!!.title
                body.text = response.body()!!.body
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
            }
        })
    }

    private fun getComments() {
        val call = RetrofitClient.getInstance().getApi().getComments(intent.getIntExtra("id", 0))
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val commentList: ArrayList<Comment> = response.body() as ArrayList<Comment>
                val titleList = ArrayList<String>()
                val idList = ArrayList<Int>()
                val bodyList = ArrayList<String>()
                for (i in commentList) {
                    titleList.add(i.name)
                    bodyList.add(i.body)
                    idList.add(i.id)
                }
                adapter = Adapter(this@OneByOne, Item.getData(titleList, bodyList, idList))
                recycler.adapter = adapter
            }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
            }
        })
    }
}