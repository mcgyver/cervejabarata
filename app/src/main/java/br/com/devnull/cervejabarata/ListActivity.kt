package br.com.devnull.cervejabarata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import br.com.devnull.cervejabarata.adapters.PromotionAdapter
import br.com.devnull.cervejabarata.models.User
import br.com.devnull.cervejabarata.utils.Const
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import com.google.firebase.database.FirebaseDatabase
import com.vicpin.krealmextensions.allItems

class ListActivity : AppCompatActivity() {

    private val TAG = "ListActivity"
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("val")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        fab.onClick {
            startActivity<NewPromotionActivity>()
        }

        var promocoes = Const.Promotions()

        recycler.setHasFixedSize(true)
        val mLayoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = mLayoutManager
        val adapter = PromotionAdapter(promocoes)
        recycler.adapter = adapter
    }

    override fun onContextMenuClosed(menu: Menu?) {
        super.onContextMenuClosed(menu)

        menuInflater.inflate(R.menu.menu_list, menu)
    }
}
