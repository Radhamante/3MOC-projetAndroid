package com.example.projet.ui.list.ClassementTitre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.model.ClassementTitre
import com.example.projet.model.generateFakeTitre
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_bottom_navigation_classement.view.*
import kotlinx.android.synthetic.main.titre_item_cell.view.*

class ClassementTitreList : Fragment() {

    // Equivalent du setContentView : qu'afficher à l'écran ?
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        print("coucou");
        // Similaire à setContentView(R.layout.list_fragment)
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Afficher les éléments les uns en dessous des autres
        view.classementTitreList.layoutManager = LinearLayoutManager(requireContext())
        // Afficher un séparateur entre les éléments
        view.classementTitreList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        // Donner les données à la liste
        view.classementTitreList.adapter = CalendarAdapter(
            titres = listOf(
                generateFakeTitre(),
                generateFakeTitre(),
                generateFakeTitre(),
                generateFakeTitre(),
                generateFakeTitre(),
            )
        )
    }

}


class CalendarAdapter(private val titres: List<ClassementTitre>) :

    RecyclerView.Adapter<TitreViewHolder>() {

    companion object {

        const val TYPE_TITLE = 0
        const val TYPE_ARTIST = 1
        const val TYPE_ALBUM = 2

    }

    override fun getItemCount(): Int {
        return titres.size
    }

    // Comment créer une cellule
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitreViewHolder {
//        if (viewType == TYPE_TITLE) {
//            // return "Title" View Holder
//        } else if (viewType == TYPE_ALBUM) {
//            // return "Album" View Holder
//        }


        return TitreViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.titre_item_cell, parent, false
            )
        )
    }

    // Comment mettre à jour une cellule
    override fun onBindViewHolder(holder: TitreViewHolder, position: Int) {
        holder.updateItem(
            oneTitre = titres[position]
        )
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_TITLE
        } else if (position == 1) {
            return TYPE_ARTIST
        } else {
            return TYPE_ALBUM
        }
    }

}

// Une cellule
class TitreViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var titre: TextView = v.titreCellTitre
    val artiste: TextView = v.titreCellArtiste
    val image: ImageView = v.titreCellImage

    fun updateItem(oneTitre: ClassementTitre) {
        titre.text = oneTitre.titre
        artiste.text = oneTitre.artiste

        Picasso.get().load(oneTitre.imageUrl).into(image)
    }

}