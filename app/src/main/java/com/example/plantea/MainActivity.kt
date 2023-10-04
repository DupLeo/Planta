package com.example.plantea

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageCollection = findViewById<ImageView>(R.id.imageCollection)

        imageCollection.setOnClickListener {
            // Créez l'intention d'aller vers ExpenseActivity
            val intent = Intent(applicationContext, CollectionActivity::class.java)
            startActivity(intent)
        }
    }
}
