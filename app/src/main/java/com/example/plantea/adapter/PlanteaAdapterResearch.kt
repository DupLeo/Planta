package com.example.plantea.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantea.R

class PlanteaAdapterResearch(private val context: Context): RecyclerView.Adapter<PlanteaAdapterResearch.PlanteaHolderR>() {

    class PlanteaHolderR(itemView: View) :  RecyclerView.ViewHolder(itemView){
        val imagePlante: ImageView = itemView.findViewById(R.id.image_plante)
        val name: TextView = itemView.findViewById(R.id.name_plante)
        val famille: TextView = itemView.findViewById(R.id.famille_plante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanteaAdapterResearch.PlanteaHolderR {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return PlanteaAdapterResearch.PlanteaHolderR(view)
    }

    override fun onBindViewHolder(holder: PlanteaHolderR, position: Int) {
        holder.imagePlante.setImageResource(R.drawable.ic_launcher_foreground) // ici mettre le lien de la photo avec 'fleur.photo'
        holder.name.text = "le nom"
        holder.famille.text = "la famille de la plante"
    }

    override fun getItemCount(): Int {
        return 10
    }
}