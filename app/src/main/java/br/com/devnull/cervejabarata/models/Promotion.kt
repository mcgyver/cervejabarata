package br.com.devnull.cervejabarata.models

import com.google.android.gms.maps.model.LatLng
import io.realm.RealmObject
import java.util.*

data class Promotion(
        val id: UUID,
        val beerName: String,
        val beerPrice: Double,
        val beerPlace: String,
        val latLng: LatLng,
        val image: String) : RealmObject() {
    init {

    }
}


