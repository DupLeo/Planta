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
        TODO("Not yet implemented")
    }

    override fun cursorToObject(cursor: Cursor): Plante {
        TODO("Not yet implemented")
    }
}