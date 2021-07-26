package com.example.projet.ui.list.artiste

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.API.ArtistData
import com.example.projet.API.ArtistDataContent
import com.example.projet.ui.artisteDetail.ArtisteDetail
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtisteAdapter(private val listArtiste: ArtistData) : RecyclerView.Adapter<ArtisteCell>() {

    override fun getItemCount(): Int {
        return listArtiste.content.size
        //return 5
    }

    // Comment créer une cellule
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtisteCell {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.artiste_cell, parent, false)

        return ArtisteCell(view)
    }

    // Comment mettre à jour une cellule
    override fun onBindViewHolder(holder: ArtisteCell, position: Int) {
        return holder.updateCell(
            listArtiste.content[position]
        )
    }
}

// Une cellule
class ArtisteCell(v: View) : RecyclerView.ViewHolder(v) {
    private val imageArtiste: ImageView = v.findViewById(R.id.image_artiste)
    private val titleArtiste: TextView = v.findViewById(R.id.title_artiste)
    var idArtiste = ""

//      GOTO artiste page
    init {
        titleArtiste.setOnClickListener {
            val intent = Intent(v.context, ArtisteDetail::class.java)
            GlobalScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    v.context.startActivity(intent)
                }
            }
        }
    }

    fun updateCell(artiste: ArtistDataContent) {
        titleArtiste.text = artiste.strArtist
        idArtiste = artiste.idArtist
        if (artiste.strArtistThumb != null && artiste.strArtistThumb != "") {
            Picasso.get().load(artiste.strArtistThumb).into(imageArtiste)
        }

        val transformation: Transformation = RoundedTransformationBuilder()
            .borderColor(Color.BLACK)
            .cornerRadiusDp(50f)
            .oval(false)
            .build()

        Picasso.get().load(artiste.strArtistThumb)
            .fit()
            .transform(transformation)
            .into(imageArtiste)
    }

}