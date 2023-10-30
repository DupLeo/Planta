package com.example.plantea.request

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.plantea.model.Plante

class PlanteaRequest(context: Context) {
    interface RequestListener {
        fun onRequestComplete(plantes: List<Plante>)
    }

    private var listener: RequestListener? = null
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private val listPlante = mutableListOf<Plante>()

    fun setListener(requestListener: RequestListener) {
        this.listener = requestListener
    }

    fun makeRequest() {
        val url = "https://trefle.io/api/v1/plants/search?token=t2v4bgJ9oGha__W2WONYV2m-Oqg_8eVwgCji3qNlC4I&q=coconut" // Remplacez YOUR_API_TOKEN par votre jeton API

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                // Traitement de la réponse JSON ici
                val jsonArray = response.getJSONArray("data")

                val limit = 10 // Limite de 10 éléments
                val actualLimit = if (jsonArray.length() < limit) jsonArray.length() else limit

                for (i in 0 until actualLimit) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    var name = jsonObject.getString("scientific_name")
                    if(jsonObject.getString("common_name") != "null") {
                        name = jsonObject.getString("common_name")
                    }
                    val family = jsonObject.getString("family")
                    val photo = jsonObject.optString("image_url", "null")

                    val fleur = Plante(i, name, family, photo)
                    listPlante.add(fleur)
                }

                listener?.onRequestComplete(listPlante) // Appel de la fonction onRequestComplete
            },
            { error ->
                println(error)
                // Gérer les erreurs ici
            }
        )

        requestQueue.add(request)
    }
    fun searchPlants(query: String?) {
        val url = "https://trefle.io/api/v1/plants/search?token=t2v4bgJ9oGha__W2WONYV2m-Oqg_8eVwgCji3qNlC4I&q=$query"

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                // Traitement de la réponse JSON ici
                listPlante.clear()

                val jsonArray = response.getJSONArray("data")

                val limit = 10 // Limite de 10 éléments
                val actualLimit = if (jsonArray.length() < limit) jsonArray.length() else limit

                for (i in 0 until actualLimit) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    var name = jsonObject.getString("scientific_name")
                    if (jsonObject.getString("common_name") != "null") {
                        name = jsonObject.getString("common_name")
                    }
                    val family = jsonObject.getString("family")
                    val photo = jsonObject.optString("image_url", "null")

                    val fleur = Plante(i, name, family, photo)
                    listPlante.add(fleur)
                }

                listener?.onRequestComplete(listPlante)
            },
            { error ->
                println(error)
                // Gérer les erreurs ici
            }
        )

        requestQueue.add(request)
    }
}
