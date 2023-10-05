package com.example.plantea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val imageSearch =  findViewById<ImageView>(R.id.imageSearch)
        val imageCollection = findViewById<ImageView>(R.id.imageCollection)

        imageSearch.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        imageCollection.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, CollectionActivity::class.java)
            startActivity(intent)
        }
    }
}