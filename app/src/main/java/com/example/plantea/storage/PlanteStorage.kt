package com.example.plantea.storage

import android.content.Context
import com.example.plantea.model.Plante
import com.example.plantea.storage.utility.Storage

object PlanteStorage {

    fun get(context: Context): Storage<Plante> {
        lateinit var storage: Storage<Plante>
        storage = PlanteJSONFileStorage(context)
        return storage
    }
}