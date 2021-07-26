package com.example.projet.ui.mainBottomNavigation

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.example.projet.ui.list.artiste.ArtisteAdapter
import com.example.projet.API.ArtistData
import com.example.projet.API.NetworkArtist
import kotlinx.android.synthetic.main.fragment_bottom_navigation_recherche.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomNavigationRechercheFragment : Fragment() {
    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation_recherche, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Artistes
        val recyclerViewArtistes = view.findViewById<RecyclerView>(R.id.list_artistes)


        val search = view.findViewById<EditText>(R.id.search_bar)
        search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(
                v: TextView?,
                actionId: Int,
                event: KeyEvent?
            ): Boolean {
                GlobalScope.launch(Dispatchers.Default) {
                    try {
                        withContext(Dispatchers.Main) {
                            val search = v?.findViewById<EditText>(R.id.search_bar)
                            val dataArtistes: ArtistData =
                                NetworkArtist.api.getArtistsDetailsByArtistNameDataAsync(search?.text.toString())
                                    .await()

                            recyclerViewArtistes.apply {
                                view.list_artistes.layoutManager =
                                    LinearLayoutManager(requireContext())
                                if (dataArtistes.content != null) {
                                    view.list_artistes.adapter = ArtisteAdapter(dataArtistes)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                        }
                    }
                }
                return false
            }
        })
    }
}