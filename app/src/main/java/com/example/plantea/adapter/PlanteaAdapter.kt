package com.example.plantea.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantea.R

class PlanteaAdapter: RecyclerView.Adapter<PlanteaAdapter.PlanteaHolder>() {

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
        return 10
    }

    override fun onBindViewHolder(holder: PlanteaHolder, position: Int) {
        holder.imagePlante.setImageResource(R.drawable.ic_launcher_foreground)
        holder.name.text = "Le nom de la belle plante"
        holder.famille.text = "La famille de la belle plante"
    }
}