package com.example.plantea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantea.adapter.PlanteaAdapter

class CollectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        val imageSearch =  findViewById<ImageView>(R.id.imageSearch)
        val imagePicture = findViewById<ImageView>(R.id.imagePicture)

        imageSearch.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        imagePicture.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, PictureActivity::class.java)
            startActivity(intent)
        }

        val list : RecyclerView = findViewById(R.id.plante_liste)
        list.adapter = PlanteaAdapter(applicationContext)
    }
}