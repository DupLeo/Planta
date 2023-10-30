package com.example.plantea

import PlanteaRequest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantea.adapter.PlanteaAdapterResearch
import com.example.plantea.model.Plante

class MainActivity : AppCompatActivity(), PlanteaRequest.RequestListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val planteaRequest = PlanteaRequest(applicationContext)
        planteaRequest.setListener(this)
        planteaRequest.makeRequest()
    }

    override fun onRequestComplete(plantes: List<Plante>) {
        val list: RecyclerView = findViewById(R.id.liste_recherche)
        val adapter = PlanteaAdapterResearch(applicationContext, plantes)
        list.adapter = adapter
    }
}
