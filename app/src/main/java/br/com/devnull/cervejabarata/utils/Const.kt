package br.com.devnull.cervejabarata.utils

import br.com.devnull.cervejabarata.models.Promotion

class Const {
    companion object {
        fun Promocoes(): ArrayList<Promotion> {
            val promocoes: ArrayList<Promotion> = arrayListOf()
            promocoes.add(Promotion(1, "Skol", 2.5, "Mecado do Zé"))
            promocoes.add(Promotion(2, "Bhrama", 2.3, "Mecado do Zé"))
            promocoes.add(Promotion(3, "Skol", 2.6, "Mecado da  Esquina"))
            promocoes.add(Promotion(4, "Antartica", 2.2, "Mecado da Esquina"))
            promocoes.add(Promotion(5, "Kaizer", 2.0, "Posto"))

            return promocoes
        }
    }
}