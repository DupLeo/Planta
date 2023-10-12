package com.example.plantea
import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Locale.*


class PictureActivity : AppCompatActivity() {
    private lateinit var imagePhoto: ImageView
    private val REQUEST_CAMERA_PERMISSION =
        1
    private var imageCaptured = false // Variable de drapeau



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)


        imagePhoto = findViewById(R.id.BoutonPhoto)
        val buttonSave = findViewById<Button>(R.id.buttonSauvegarde)


        // ====================================== Menu clicks ===================================//
        val imageSearch = findViewById<ImageView>(R.id.imageSearch)
        val imageCollection = findViewById<ImageView>(R.id.imageCollection)

        imageSearch.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        imageCollection.setOnClickListener {
            val intent = Intent(applicationContext, CollectionActivity::class.java)
            startActivity(intent)
        }
        //========================================================================================//

        val takePhoto =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                if (bitmap != null) {
                    imagePhoto.setImageBitmap(bitmap)
                    imageCaptured = true
                }
            }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Demande la permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            imagePhoto.setOnClickListener { takePhoto.launch(null) }
        }

        buttonSave.setOnClickListener {
            // Sauvegarde l'image ici, si nécessaire
            if (imageCaptured) {
                // Une image a été capturée, vous pouvez la sauvegarder
                saveImageToFile(imagePhoto.drawable.toBitmap())
            } else {
                // Aucune image n'a été capturée, affichez un message d'erreur
                showToast("Prenez un photo")
            }
        }

    }
    private fun saveImageToFile(bitmap: Bitmap) {
        val nomCommun = findViewById<EditText>(R.id.InputNom).text.toString()
        val nomDeFamille = findViewById<EditText>(R.id.InputNomDeFamille).text.toString()

        if (nomCommun.isEmpty() && nomDeFamille.isEmpty()) {
            showToast("Veuillez saisir un nom commun et un nom de famille avant de sauvegarder l'image.")
            return
        }
        val imageFileName = "${nomCommun}_${nomDeFamille}.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = contentResolver
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            val outputStream = resolver.openOutputStream(imageUri!!)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream?.close()

            // Récupérer l'URI de l'image sauvegardée
            val savedImageUri = imageUri.toString()

            showToast("Image enregistrée avec succès. Lien : $savedImageUri")

            // Réinitialiser le drapeau imageCaptured à false
            imageCaptured = false
        } catch (e: IOException) {
            e.printStackTrace()
            showToast("Échec de l'enregistrement de l'image.")
        }
    }





    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}