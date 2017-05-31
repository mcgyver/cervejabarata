package br.com.devnull.cervejabarata


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_new_promotion.*
import com.google.android.gms.maps.CameraUpdateFactory


class NewPromotionActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private val CAMERA_REQUEST = 1888
    private var location: Location? = null

    override fun onMapReady(map: GoogleMap?) {
        val center = CameraUpdateFactory.newLatLng(LatLng(location!!.latitude, location!!.longitude))
        val zoom = CameraUpdateFactory.zoomTo(15F)

        map?.moveCamera(center)
        map?.animateCamera(zoom)
        map?.addMarker(MarkerOptions()
                .position(LatLng(location!!.latitude, location!!.longitude)).title("Marker"))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_promotion)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setLocation()

        photo_button.setOnClickListener {
            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

    private fun setLocation(){
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        //Toast.makeText(baseContext, loc.latitude.toString(), Toast.LENGTH_LONG).show()
        //locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 5F, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val photo = data.extras.get("data") as Bitmap
            foto_nova_promocao.setImageBitmap(photo)
        }
    }

    override fun onLocationChanged(location: Location?) {
        Toast.makeText(baseContext, "Current Location: $location?.getLatitude()  $location?.getLongitude()", Toast.LENGTH_LONG).show()
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Toast.makeText(baseContext, "NOVO", Toast.LENGTH_LONG).show()
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
