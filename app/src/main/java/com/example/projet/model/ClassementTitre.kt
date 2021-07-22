package com.example.projet.model

import android.os.Parcelable
import com.g123k.myapplication.NutriScore
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClassementTitre (
    val titre: String,
    val artiste: String,
    val classement: Int,
    val imageUrl: String,
) : Parcelable

fun generateFakeTitre() = ClassementTitre(
    titre = "oui",
    artiste = "ouideouf",
    classement = 1,
    imageUrl = "https://thispersondoesnotexist.com/"
)