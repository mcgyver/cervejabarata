package br.com.devnull.cervejabarata

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import br.com.devnull.cervejabarata.R.id.action_settings
import br.com.devnull.cervejabarata.adapters.PromotionAdapter
import br.com.devnull.cervejabarata.models.Promotion
import br.com.devnull.cervejabarata.models.User
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.vicpin.krealmextensions.firstItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener



class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val database : DatabaseReference = FirebaseDatabase.getInstance().reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        Realm.init(applicationContext)
        var user = User().firstItem

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        val hView = navigationView.getHeaderView(0)
        val navHeaderName = hView.findViewById(R.id.nav_header_name) as TextView
        val navHeaderImage = hView.findViewById(R.id.nav_header_image) as ImageView
        navHeaderName.text = user?.name
        Picasso.with(applicationContext).load(user?.photoUrl).into(navHeaderImage)

        fab.onClick {
            startActivity<NewPromotionActivity>()
        }

        var promotions = arrayListOf<Promotion>()
        val ref = database.child("promotions")
        val query = ref.limitToLast(100)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (singleSnapshot in dataSnapshot.children) {
                    promotions.add(singleSnapshot.getValue(Promotion::class.java))
                    val adapter = PromotionAdapter(promotions)
                    recycler.adapter = adapter
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        recycler.setHasFixedSize(true)
        val mLayoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = mLayoutManager

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()


        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        var promotions = arrayListOf<Promotion>()
        val ref = database.child("promotions")
        val query = ref.limitToLast(100)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (singleSnapshot in dataSnapshot.children) {
                    promotions.add(singleSnapshot.getValue(Promotion::class.java))
                    val adapter = PromotionAdapter(promotions)
                    recycler.adapter = adapter
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == action_settings) return true

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_exit) {
            LoginManager.getInstance().logOut()
            FirebaseAuth.getInstance().signOut()
            startActivity<MainActivity>()
            finish()
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
