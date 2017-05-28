package br.com.devnull.cervejabarata.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.devnull.cervejabarata.R
import br.com.devnull.cervejabarata.models.Promotion


class PromocaoAdapter(private var promocoes: ArrayList<Promotion>) : RecyclerView.Adapter<PromocaoViewHolder>() {

    override fun onBindViewHolder(holder: PromocaoViewHolder?, position: Int) {
        val promocao = promocoes[position]
        if (holder != null) {
            holder.cerveja.text = promocao.nomeCerveja
            holder.mercado.text = promocao.local
            holder.valor.text = promocao.valor.toString()
        }
    }

    override fun getItemCount(): Int = promocoes.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PromocaoViewHolder? {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.promocao_layout, parent, false)
        return PromocaoViewHolder(view)
    }

}

