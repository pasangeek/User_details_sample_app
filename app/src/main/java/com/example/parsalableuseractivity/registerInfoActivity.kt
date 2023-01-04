package com.example.parsalableuseractivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : AppCompatActivity() {
    //private lateinit var button: Button
    private lateinit var imageView: ShapeableImageView
    private val pickImage = 100
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // button = findViewById(R.id.button_Image_pick)
        imageView = findViewById(R.id.imageView2)
        val image_pick_Button = findViewById<Button>(R.id.button_Image_pick)
        val submit_button = findViewById<Button>(R.id.btnApply)
        val user_Name = findViewById<EditText>(R.id.etName)
        val user_EmailAddress = findViewById<EditText>(R.id.etEmailAddress)
        val user_Age = findViewById<EditText>(R.id.editTextAge)
        val user_phone = findViewById<EditText>(R.id.Ephone)

        submit_button.setOnClickListener {
            val name = user_Name.text.toString()
            val email = user_EmailAddress.text.toString()
            val age = user_Age.text.toString().toString()
            val phone = user_phone.text.toString().toString()
            val profilePictureUri = imageUri.toString()

            val user = User(name, email, age, phone, profilePictureUri)

            Intent(this, profileActivity::class.java).also {
                it.putExtra("EXTRA_USER", user)


                startActivity(it)
            }
        }

        image_pick_Button.setOnClickListener {

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }


    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)

        }

    }
}