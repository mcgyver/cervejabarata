package br.com.devnull.cervejabarata.models

import com.google.android.gms.maps.model.LatLng
import io.realm.RealmObject
import java.util.*

data class Promotion(
        val id: String,
        val beerName: String,
        val beerPrice: Double,
        val beerPlace: String,
        val latitude: Double,
        val longitude: Double,
        val image: String) : RealmObject() {
    constructor() : this ("", "", 0.0, "", 0.0, 0.0, "")
}


