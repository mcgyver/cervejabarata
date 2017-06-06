package br.com.devnull.cervejabarata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import br.com.devnull.cervejabarata.adapters.PromotionAdapter
import br.com.devnull.cervejabarata.models.Promotion
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import java.util.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        fab.onClick {
            startActivity<NewPromotionActivity>()
        }

        var promotion : ArrayList<Promotion> = arrayListOf()

        recycler.setHasFixedSize(true)
        val mLayoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = mLayoutManager
        val adapter = PromotionAdapter(promotion)
        recycler.adapter = adapter
    }

    override fun onContextMenuClosed(menu: Menu?) {
        super.onContextMenuClosed(menu)
        menuInflater.inflate(R.menu.menu_list, menu)
    }
}
