package br.com.devnull.cervejabarata.utils

import br.com.devnull.cervejabarata.models.Promotion

class Const {
    companion object {
        fun Promotions(): ArrayList<Promotion> {
            val promotions: ArrayList<Promotion> = arrayListOf()
            promotions.add(Promotion(1, "Skol", 2.5, "Mecado do Zé"))
            promotions.add(Promotion(2, "Bhrama", 2.3, "Mecado do Zé"))
            promotions.add(Promotion(3, "Skol", 2.6, "Mecado da  Esquina"))
            promotions.add(Promotion(4, "Antartica", 2.2, "Mecado da Esquina"))
            promotions.add(Promotion(5, "Kaizer", 2.0, "Posto"))
            promotions.add(Promotion(1, "Skol", 2.5, "Mecado do Zé"))
            promotions.add(Promotion(2, "Bhrama", 2.3, "Mecado do Zé"))
            promotions.add(Promotion(3, "Skol", 2.6, "Mecado da  Esquina"))
            promotions.add(Promotion(4, "Antartica", 2.2, "Mecado da Esquina"))
            promotions.add(Promotion(5, "Kaizer", 2.0, "Posto"))

            return promotions
        }
    }
}
