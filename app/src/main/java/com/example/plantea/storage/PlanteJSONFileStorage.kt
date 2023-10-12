package com.example.plantea.storage

import android.content.Context
import com.example.plantea.model.Plante
import com.example.plantea.storage.utility.file.JSONFileStorage
import org.json.JSONObject

class PlanteJSONFileStorage(context: Context): JSONFileStorage<Plante>(context, "plante") {
    override fun create(id: Int, obj: Plante): Plante {
        return Plante(id, obj.name, obj.famille, obj.photo)
    }

    override fun objectToJson(id: Int, obj: Plante): JSONObject {
        val json = JSONObject()
        json.put(Plante.ID, id)
        json.put(Plante.NAME, obj.name)
        json.put(Plante.FAMILLE, obj.famille)
        json.put(Plante.PHOTO, obj.photo)
        return json
    }

    override fun jsonToObject(json: JSONObject): Plante {
        // on retourne l'objet Plante
        return Plante(
            json.getInt(Plante.ID),
            json.getString(Plante.NAME),
            json.getString(Plante.FAMILLE),
            json.getString(Plante.PHOTO)

        )
    }
}