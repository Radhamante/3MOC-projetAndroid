package com.example.projet.ui.list.classementTitre

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.API.NetworkTopTitle
import com.example.projet.API.TopTitleDataContent
import com.example.projet.ui.parole.Parole
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.fragment_classement_titre_list.view.*
import kotlinx.android.synthetic.main.titre_item_cell.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClassementTitreList : Fragment() {

    // Equivalent du setContentView : qu'afficher à l'écran ?
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_classement_titre_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Afficher les éléments les uns en dessous des autres
        view.classementTitreList.layoutManager = LinearLayoutManager(requireContext())

        GlobalScope.launch(Dispatchers.Default) {
            val dataTopTitle = NetworkTopTitle.api.getTopTitleDataAsync().await()
            print(dataTopTitle)

            withContext(Dispatchers.Main) {
                // Donner les données à la liste
                view.classementTitreList.adapter = TopTitlesAdapter(
                    titles = dataTopTitle.content.sortedBy { it.intChartPlace }
                )
            }
        }


    }

}


class TopTitlesAdapter(private val titles: List<TopTitleDataContent>) :

    RecyclerView.Adapter<TitleViewHolder>() {

    companion object {

        const val TYPE_TITLE = 0
        const val TYPE_ARTIST = 1
        const val TYPE_ALBUM = 2

    }

    override fun getItemCount(): Int {
        return titles.size
    }

    // Comment créer une cellule
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
//        if (viewType == TYPE_TITLE) {
//            // return "com.example.projet.ui.album.Title" View Holder
//        } else if (viewType == TYPE_ALBUM) {
//            // return "com.example.projet.ui.album.Album" View Holder
//        }


        return TitleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.titre_item_cell, parent, false
            )
        )
    }

    // Comment mettre à jour une cellule
    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.updateItem(
            oneTitle = titles[position]
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
class TitleViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    val viewTitre: TextView = v.titreCellTitre
    val viewArtist: TextView = v.titreCellArtiste
    val viewThumbnail: ImageView = v.titreCellImage
    val viewCellNumber: TextView = v.titreCellNumber

    var id = ""

    init {
        viewTitre.setOnClickListener {
            val intent = Intent(v.context, Parole::class.java)
            GlobalScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    intent.putExtra("id", id);
                    v.context.startActivity(intent)
                }
            }
        }
    }

    fun updateItem(oneTitle: TopTitleDataContent) {

        viewTitre.text = oneTitle.strTrack
        viewArtist.text = oneTitle.strArtist
        viewCellNumber.text = oneTitle.intChartPlace

        this.id = oneTitle.idTrend


        val transformation: Transformation = RoundedTransformationBuilder()
            .borderColor(Color.BLACK)
            .cornerRadiusDp(5f)
            .oval(false)
            .build()

        Picasso.get().load(oneTitle.strTrackThumb)
            .fit()
            .transform(transformation)
            .into(viewThumbnail)

    }

}