package com.example.plantea.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.plantea.helper.PlanteaDataBaseHelper
import com.example.plantea.model.Plante
import com.example.plantea.storage.utility.DataBaseStorage

class PlanteDataBaseStorage(context: Context): DataBaseStorage<Plante>(
    PlanteaDataBaseHelper(context), "plante"
) {
    override fun objectToValues(obj: Plante): ContentValues {
        val values = ContentValues()
        values.put(Plante.ID, obj.id)
        values.put(Plante.NAME, obj.name)
        values.put(Plante.FAMILLE, obj.famille)
        values.put(Plante.PHOTO, obj.photo)
        return values
    }

    override fun cursorToObject(cursor: Cursor): Plante {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Plante.ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Plante.NAME))
        val famille = cursor.getString(cursor.getColumnIndexOrThrow(Plante.FAMILLE))
        val photo = cursor.getString(cursor.getColumnIndexOrThrow(Plante.PHOTO))
        return Plante(id, name, famille, photo)
    }
}