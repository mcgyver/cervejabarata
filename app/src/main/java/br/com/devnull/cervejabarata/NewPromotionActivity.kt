package br.com.devnull.cervejabarata

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import br.com.devnull.cervejabarata.models.Promotion
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_new_promotion.*
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.onClick
import java.io.ByteArrayOutputStream
import java.util.*
import android.view.MenuInflater
import android.view.MenuItem


class NewPromotionActivity : android.support.v7.app.AppCompatActivity() {

    private val CAMERA_REQUEST = 1
    private val PLACE_PICKER_REQUEST = 2

    private var place: Place? = null

    var storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val databaseRef: DatabaseReference = database.getReference("promotions")


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_promotion)



        choose_place_button.onClick {
            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)
        }
        photo_button.onClick {
            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }

        /*ok_button.onClick {
            val id = UUID.randomUUID()
            val mountainsRef = storageRef.child(id.toString())
            var imageUrl : String

            val drawable = photo_new_promotion?.drawable as BitmapDrawable
            val bitmap = drawable.bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val imageInBytes = stream.toByteArray()

            val uploadTask = mountainsRef.putBytes(imageInBytes)
            uploadTask.addOnFailureListener({

            }).addOnSuccessListener({ taskSnapshot ->
                imageUrl = taskSnapshot.downloadUrl.toString()
                val promotion = Promotion(id.toString(),
                        beerName = tie_new_beer_name.text.toString(),
                        beerPlace = place?.name.toString(),
                        beerPrice = tie_new_beer_price.text.toString().toDouble(),
                        latitude = place?.latLng?.latitude!!,
                        longitude = place?.latLng?.longitude!!,
                        image = imageUrl)
                databaseRef.child(id.toString()).setValue(promotion)
                Toast.makeText(baseContext, "Promoção salva com sucesso!", Toast.LENGTH_LONG).show()
                finish()
            })
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val image = intent?.extras?.get("data") as Bitmap?
            photo_new_promotion?.setImageBitmap(image)
            tvi_take_a_pic.visibility = View.INVISIBLE

        }
        else if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            place =  PlacePicker.getPlace(this, intent)
            choose_place_button.text =  place?.name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_new_promotion, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_new_promotion_ok -> {
                val id = UUID.randomUUID()
                val mountainsRef = storageRef.child(id.toString())
                var imageUrl : String

                val drawable = photo_new_promotion?.drawable as BitmapDrawable
                val bitmap = drawable.bitmap
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val imageInBytes = stream.toByteArray()

                val uploadTask = mountainsRef.putBytes(imageInBytes)
                uploadTask.addOnFailureListener({

                }).addOnSuccessListener({ taskSnapshot ->
                    imageUrl = taskSnapshot.downloadUrl.toString()
                    val promotion = Promotion(id.toString(),
                            beerName = tie_new_beer_name.text.toString(),
                            beerPlace = place?.name.toString(),
                            beerPrice = tie_new_beer_price.text.toString().toDouble(),
                            latitude = place?.latLng?.latitude!!,
                            longitude = place?.latLng?.longitude!!,
                            image = imageUrl)
                    databaseRef.child(id.toString()).setValue(promotion)
                    Toast.makeText(baseContext, "Promoção salva com sucesso!", Toast.LENGTH_LONG).show()
                    finish()
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
