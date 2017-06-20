package br.com.devnull.cervejabarata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_view_promotion.*
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions





class ViewPromotionActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onMapReady(map: GoogleMap) {
        map.addMarker(MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker"))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_promotion)
        view_beer_name.text = intent.extras.getString("cerveja")
        view_beer_market.text = intent.extras.getString("mercado")
        view_beer_price.text = intent.extras.getString("beerPrice")

        /*val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/

    }

}
