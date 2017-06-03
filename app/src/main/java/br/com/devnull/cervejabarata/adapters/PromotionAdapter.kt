package br.com.devnull.cervejabarata.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.devnull.cervejabarata.R
import br.com.devnull.cervejabarata.models.Promotion


class PromotionAdapter(private var promotions: ArrayList<Promotion>) : RecyclerView.Adapter<PromotionViewHolder>() {

    override fun onBindViewHolder(holder: PromotionViewHolder?, position: Int) {
        val promotion = promotions[position]
        if (holder != null) {
            holder.cerveja.text = promotion.beerName
            holder.mercado.text = promotion.beerPlace
            holder.valor.text = promotion.beerPrice.toString()
        }
    }

    override fun getItemCount(): Int = promotions.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PromotionViewHolder? {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.promocao_layout, parent, false)
        return PromotionViewHolder(view)
    }
}

