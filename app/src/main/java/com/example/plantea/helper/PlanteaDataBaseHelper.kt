package com.example.plantea.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.plantea.model.Plante

class PlanteaDataBaseHelper(context: Context): SQLiteOpenHelper(context, "plantea.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS Plantea  (" +
                    "${BaseColumns._ID} INTEGER," +
                    "${Plante.NAME} TEXT," +
                    "${Plante.FAMILLE} TEXT," +
                    "${Plante.PHOTO} TEXT," +
                    "PRIMARY KEY(${BaseColumns._ID})" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Plantea")
        if (db != null) {
            onCreate(db)
        }
    }
}