package com.example.plantea.storage

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.plantea.helper.PlanteaDataBaseHelper
import com.example.plantea.model.Plante
import com.example.plantea.storage.utility.DataBaseStorage

class PlanteDataBaseStorage(context: Context): DataBaseStorage<Plante>(
    PlanteaDataBaseHelper(context), "Plantea"
) {
    override fun objectToValues(obj: Plante): ContentValues {
        val values = ContentValues()
        //values.put(BaseColumns._ID, obj.id)
        values.put(Plante.NAME, obj.name)
        values.put(Plante.FAMILLE, obj.famille)
        values.put(Plante.PHOTO, obj.photo)
        return values
    }

    override fun cursorToObject(cursor: Cursor): Plante {
        val id = cursor.getInt(0)
        val name = cursor.getString(1)
        val famille = cursor.getString(2)
        val photo = cursor.getString(3)
        return Plante(id, name, famille, photo)
    }
}