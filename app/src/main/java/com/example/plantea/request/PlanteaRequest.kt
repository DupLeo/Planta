package com.example.plantea.request

import com.android.volley.Request
import android.content.Context
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class PlanteaRequest(private val context: Context) {


    companion object{
        private const val URL = "https://trefle.io/api/v1/plants/search?token=t2v4bgJ9oGha__W2WONYV2m-Oqg_8eVwgCji3qNlC4I&q=coconut"
    }

    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET, URL, null
            {
                    response ->
                // refresh(response)
                Toast.makeText(context, "La liste a été récupéré", Toast.LENGTH_SHORT).show()
            }
        ) { error ->
            println(error)
            Toast.makeText(context, "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
        }
    }
}