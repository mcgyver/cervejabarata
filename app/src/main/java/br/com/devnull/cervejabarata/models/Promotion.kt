package br.com.devnull.cervejabarata.models

data class Promotion(
        val id: String,
        val beerName: String,
        val beerPrice: Double,
        val beerPlace: String,
        val latitude: Double,
        val longitude: Double,
        val image: String)  {
    constructor() : this ("", "", 0.0, "", 0.0, 0.0, "")
}


