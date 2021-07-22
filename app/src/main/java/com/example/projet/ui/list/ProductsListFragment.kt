package com.g123k.myapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.projet.R
import com.g123k.myapplication.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.titre_item_cell.view.*
import kotlinx.android.synthetic.main.product_list_fragment.view.*

class ProductsListFragment : Fragment() {

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

//        // Afficher les éléments les uns en dessous des autres
//        view.list.layoutManager = LinearLayoutManager(requireContext())
//        // Afficher un séparateur entre les éléments
//        view.list.addItemDecoration(
//            DividerItemDecoration(
//                requireContext(),
//                DividerItemDecoration.VERTICAL
//            )
//        )
//
//        // Donner les données à la liste
//        view.list.adapter = CalendarAdapter(
//            products = listOf(
//                generateFakeProduct(),
//                generateFakeProduct(),
//                generateFakeProduct(),
//                generateFakeProduct(),
//                generateFakeProduct(),
//            )
//        )
    }

}


class CalendarAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {

    companion object {

        const val TYPE_TITLE = 0
        const val TYPE_ARTIST = 1
        const val TYPE_ALBUM = 2

    }

    override fun getItemCount(): Int {
        return products.size
    }

    // Comment créer une cellule
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        if (viewType == TYPE_TITLE) {
            // return "Title" View Holder
        } else if (viewType == TYPE_ALBUM) {
            // return "Album" View Holder
        }



        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.titre_item_cell, parent, false
            )
        )
    }

    // Comment mettre à jour une cellule
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.updateItem(
            product = products[position]
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
class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v) {

//    val thumbnail: ImageView = v.
//    val brand: TextView = v.product_cell_brand
//    val name: TextView = v.product_cell_name

    fun updateItem(product: Product) {
//        brand.text = product.brand
//        name.text = product.name
//
//        Picasso.get().load(product.thumbnail).into(thumbnail, object : Callback {
//            override fun onSuccess() {}
//
//            override fun onError(e: Exception?) {}
//
//        })
    }

}