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
import com.example.plantea.model.Plante

class PlanteaAdapterResearch(private val context: Context, private var plantes: List<Plante>) : RecyclerView.Adapter<PlanteaAdapterResearch.PlanteaHolderR>() {

    class PlanteaHolderR(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagePlante: ImageView = itemView.findViewById(R.id.image_plante)
        val name: TextView = itemView.findViewById(R.id.name_plante)
        val famille: TextView = itemView.findViewById(R.id.famille_plante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanteaHolderR {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return PlanteaHolderR(view)
    }

    override fun onBindViewHolder(holder: PlanteaHolderR, position: Int) {
        val fleur = plantes[position] // Accédez à la plante à la position donnée
        holder.name.text = fleur.name
        holder.famille.text = fleur.famille

        // Utilisez Glide pour charger l'image depuis l'URI de l'image de la plante
        Glide.with(context)
            .load(fleur.photo) // Ici, fleur.photo devrait contenir l'URI de l'image
            .placeholder(R.drawable.ic_launcher_foreground) // Image de remplacement en cas de chargement
            .error(R.drawable.ic_launcher_foreground) // Image de remplacement en cas d'erreur
            .into(holder.imagePlante)
    }
    fun updateData(newPlantes: List<Plante>) {
        plantes = newPlantes
        notifyDataSetChanged() // Notifiez l'adaptateur que les données ont changé
    }

    override fun getItemCount(): Int {
        return plantes.size
    }
}
