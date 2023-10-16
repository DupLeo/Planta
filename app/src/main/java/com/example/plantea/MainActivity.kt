package com.example.plantea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantea.adapter.PlanteaAdapter
import com.example.plantea.adapter.PlanteaAdapterResearch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageCollection = findViewById<ImageView>(R.id.imageCollection)
        val imagePicture = findViewById<ImageView>(R.id.imagePicture)

        imageCollection.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, CollectionActivity::class.java)
            startActivity(intent)
        }

        imagePicture.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, PictureActivity::class.java)
            startActivity(intent)
        }

        val list : RecyclerView = findViewById(R.id.liste_recherche)
        list.adapter = PlanteaAdapterResearch(applicationContext)



    }
}




