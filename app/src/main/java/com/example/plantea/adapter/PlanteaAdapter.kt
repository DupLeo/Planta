package com.example.plantea.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantea.R
import com.example.plantea.storage.PlanteStorage

class PlanteaAdapter(private val context: Context): RecyclerView.Adapter<PlanteaAdapter.PlanteaHolder>() {

    class PlanteaHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
        val imagePlante: ImageView = itemView.findViewById(R.id.image_plante)
        val name: TextView = itemView.findViewById(R.id.name_plante)
        val famille: TextView = itemView.findViewById(R.id.famille_plante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanteaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return PlanteaHolder(view)
    }

    override fun getItemCount(): Int {
        return PlanteStorage.get(context).size()
    }

    override fun onBindViewHolder(holder: PlanteaHolder, position: Int) {
        val fleur = PlanteStorage.get(context).findAll()[position]
        Glide.with(context)
            .load(fleur.photo) // Ici, fleur.photo devrait contenir l'URI de l'image
            .placeholder(R.drawable.ic_launcher_foreground) // Image de remplacement en cas de chargement
            .error(R.drawable.ic_launcher_foreground) // Image de remplacement en cas d'erreur
            .into(holder.imagePlante)
        holder.name.text = fleur.name
        holder.famille.text = fleur.famille
    }
}