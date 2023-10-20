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
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.plantea.model.Plante
import com.example.plantea.storage.PlanteDataBaseStorage
import java.io.File
import java.io.FileOutputStream
import java.util.Locale.*
import java.util.Random


class PictureActivity : AppCompatActivity() {
    private lateinit var imagePhoto: ImageView
    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_WRITE_EXTERNAL_STORAGE = 2
    private val REQUEST_MANAGE_EXTERNAL_STORAGE = 3
    private var imageCaptured = false // Variable de drapeau

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)


        imagePhoto = findViewById(R.id.BoutonPhoto)
        val buttonSave = findViewById<Button>(R.id.buttonSauvegarde)


        // ====================================== Menu clicks ====================================//

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

        //=============================   PRENDRE UNE PHOTO     ==================================//

        val takePhoto =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                if (bitmap != null) {
                    imagePhoto.setImageBitmap(bitmap)
                    imageCaptured = true
                }
            }


        // demande l'autorisation pour prendre une photo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Demande la permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Demande la permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_EXTERNAL_STORAGE
            )
        }
        imagePhoto.setOnClickListener { takePhoto.launch(null) }


        //===========================   SAUVEGARDER UNE PHOTO     ================================//

        buttonSave.setOnClickListener {
            if (imageCaptured) {
                val drawable = imagePhoto.drawable
                if (drawable != null) {
                    saveImageToFile(drawable.toBitmap())
                } else {
                    showToast("L'image capturée est nulle.")
                }
            } else {
                showToast("Prenez une photo")
            }
        }



    }

    // Enregistre une photo dans la galerie
    private fun saveImageToFile(finalBitmap: Bitmap) {
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File("$root/saved_images")
        myDir.mkdirs()
        val generator = Random()
        var n = 10000
        n = generator.nextInt(n)
        val fname = "Image-$n.jpg"
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            val filePath = file.absolutePath
            handlePlanteInsertion("nom_1", "famille_1", filePath)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun handlePlanteInsertion(nomCommun: String, nomDeFamille: String, savedImageUri: String) {
        val plante = Plante(
            id = 0,
            name = nomCommun,
            famille = nomDeFamille,
            photo = savedImageUri
        )

        val planteStorage = PlanteDataBaseStorage(this)
        val insertedId = planteStorage.insert(plante)

        if (insertedId != -1) {
            showToast("Plante enregistrée avec succès")
        } else {
            showToast("Échec de l'enregistrement de la plante.")
        }

        imageCaptured = false
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}