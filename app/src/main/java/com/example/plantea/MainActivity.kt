package com.example.plantea

import com.example.plantea.request.PlanteaRequest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantea.adapter.PlanteaAdapterResearch
import com.example.plantea.model.Plante

class MainActivity : AppCompatActivity(), PlanteaRequest.RequestListener {
    private lateinit var planteaRequest: PlanteaRequest // Déclarez la variable sans l'initialiser
    private lateinit var adapter: PlanteaAdapterResearch
    private lateinit var list: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        planteaRequest = PlanteaRequest(this)
        list = findViewById(R.id.liste_recherche)
        adapter = PlanteaAdapterResearch(applicationContext, mutableListOf())
        list.adapter = adapter

        searchView = findViewById(R.id.search_view)

        val imageCollection = findViewById<ImageView>(R.id.imageCollection)
        val imagePicture = findViewById<ImageView>(R.id.imagePicture)

        imageCollection.setOnClickListener {
            val intent = Intent(applicationContext, CollectionActivity::class.java)
            startActivity(intent)
        }

        imagePicture.setOnClickListener {
            val intent = Intent(applicationContext, PictureActivity::class.java)
            startActivity(intent)
        }

        // Écouteur de texte pour la recherche en temps réel
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performSearch(newText)
                return true
            }
        })

        planteaRequest.setListener(this)
        planteaRequest.makeRequest()
    }

    private fun performSearch(query: String?) {
        planteaRequest.searchPlants(query)
    }

    override fun onRequestComplete(plantes: List<Plante>) {
        adapter.updateData(plantes)
    }
}

