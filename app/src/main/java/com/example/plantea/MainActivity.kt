package com.example.plantea

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantea.ui.theme.PlanteaTheme

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
