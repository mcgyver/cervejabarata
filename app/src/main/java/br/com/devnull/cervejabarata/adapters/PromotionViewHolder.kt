package br.com.devnull.cervejabarata.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.devnull.cervejabarata.R
import br.com.devnull.cervejabarata.ViewPromocaoActivity

class PromotionViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    init {
        itemView.setOnClickListener {
            val intent = Intent(view.context, ViewPromocaoActivity::class.java)
            intent.putExtra("cerveja", cerveja.text)
            intent.putExtra("mercado", mercado.text)
            intent.putExtra("beerPrice", valor.text)
            view.context.startActivity(intent)
        }

    }

    internal val cerveja: TextView = view.findViewById(R.id.tvi_nome_cerveja) as TextView
    internal val mercado: TextView = view.findViewById(R.id.tvi_nome_mercado) as TextView
    internal val valor: TextView = view.findViewById(R.id.tvi_valor) as TextView




}