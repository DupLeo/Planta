package com.example.plantea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class PictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val imageSearch =  findViewById<ImageView>(R.id.imageSearch)
        val imageCollection = findViewById<ImageView>(R.id.imageCollection)
        val imagePhoto: ImageView = findViewById(R.id.BoutonPhoto)

        // ====================================== MENU ========================================== //

        imageSearch.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        imageCollection.setOnClickListener {
            val intent = Intent(applicationContext, CollectionActivity::class.java)
            startActivity(intent)
        }

        // ===================================== PHOTO ========================================== //

        // Prendre la photo
        val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
                bitmap -> if (bitmap != null) imagePhoto.setImageBitmap(bitmap)
        }
        imagePhoto.setOnClickListener{takePhoto.launch(null)}
    }
}