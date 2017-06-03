package br.com.devnull.cervejabarata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_promocao.*

class ViewPromocaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_promocao)
        view_cerveja_nome.text = intent.extras.getString("cerveja")
        view_cerveja_mercado.text = intent.extras.getString("mercado")
        view_cerveja_valor.text = intent.extras.getString("beerPrice")
    }

}
