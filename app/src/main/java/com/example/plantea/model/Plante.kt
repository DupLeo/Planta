package com.example.plantea.model

data class Plante(
    val id: Int,
    val name: String,
    val famille: String,
    val photo: String,
){
    companion object{
        const val NAME = "name"
        const val FAMILLE = "famille"
        const val PHOTO = "photo"
    }
}
