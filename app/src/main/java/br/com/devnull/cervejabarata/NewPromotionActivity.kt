package br.com.devnull.cervejabarata

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import br.com.devnull.cervejabarata.models.Promotion
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_new_promotion.*
import org.jetbrains.anko.onClick
import java.io.ByteArrayOutputStream
import java.util.*


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

        ok_button.onClick {
            val id = UUID.randomUUID()
            val mountainsRef = storageRef.child(id.toString())
            var imageUrl = ""

            Realm.init(applicationContext)

            photo_new_promotion.isDrawingCacheEnabled = true
            photo_new_promotion.buildDrawingCache()

            val bitmap = photo_new_promotion.drawingCache
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = mountainsRef.putBytes(data)
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
                //promotion.save()
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val photo = intent.extras.get("data") as Bitmap?
            photo_new_promotion.setImageBitmap(photo)
        }
        else if (requestCode === PLACE_PICKER_REQUEST && resultCode === Activity.RESULT_OK) {
                place =  PlacePicker.getPlace(this, intent)
                choose_place_button.text =  place?.name
        }
    }

}
