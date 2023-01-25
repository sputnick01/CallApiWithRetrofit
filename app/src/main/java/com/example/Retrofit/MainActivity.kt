package com.example.Retrofit

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Retrofit.adapter.RecyclerAdapter
import com.example.Retrofit.model.MovieResponse
import com.example.Retrofit.network.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initialRecyclerView()

        button_search.setOnClickListener(this)


    }

    private fun initialRecyclerView() {

        recyclerAdapter = RecyclerAdapter(this)
        recyclerview_movie.layoutManager = LinearLayoutManager(this)
        recyclerview_movie.adapter = recyclerAdapter

    }

    fun hideKeyboard() {
        val view: View = currentFocus ?: View(applicationContext)
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            button_search.id -> {


                val apiInterface = ApiInterface.create().getMovies(edittext_search.text.toString())


                progress?.visibility = View.VISIBLE

                text_dataNotFound.visibility = View.GONE

                edittext_search.text.clear()

                apiInterface.enqueue(object : retrofit2.Callback<MovieResponse> {

                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        progress?.visibility = View.GONE
                        response.body()?.search?.let { it1 ->
                            recyclerAdapter.setMovieListItem(it1)
                            hideKeyboard()

                        }
                            ?: let {
                                Toast.makeText(
                                    applicationContext,
                                    "Not Data Found",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                                recyclerAdapter.setMovieListItem(listOf())
                                hideKeyboard()
                                text_dataNotFound.visibility = View.VISIBLE
                            }


                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        progress?.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT)
                            .show()

                    }

                })


            }
        }

    }

}