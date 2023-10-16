package com.example.plantea.storage.utility

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

abstract class DataBaseStorage<T>(private val helper: SQLiteOpenHelper, private val table: String): Storage<T> {

    protected abstract fun objectToValues(obj: T): ContentValues
    protected abstract fun cursorToObject(cursor: Cursor): T

    override fun insert(obj: T): Int {
        return helper.writableDatabase.insert(table, null, objectToValues(obj)).toInt()
    }

    override fun size(): Int {
        return helper.readableDatabase.query(table,
            null, null, null, null, null,null,null).count
    }

    override fun find(id: Int): T? {
        var obj: T? = null
        // requete sql et récupère un tableau de données
        val cursor = helper.readableDatabase.query(table,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf("$id"),
            null,
            null,
            null,
            null)
        // on traite le retour de la requete
        if (cursor.moveToNext()){
            obj = cursorToObject(cursor)
        }
        cursor.close()
        return obj
    }

    override fun findAll(): List<T> {
        val liste: MutableList<T> = mutableListOf()
        val cursor: Cursor = helper.readableDatabase.query(table, null, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val element: T = cursorToObject(cursor)
            liste.add(element)
        }
        cursor.close()
        return liste
    }

    override fun update(id: Int, obj: T) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}