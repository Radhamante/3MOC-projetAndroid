package com.example.projet.ui.artisteDetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.example.projet.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class ArtisteDetailFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artiste_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }
}