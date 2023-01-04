package com.example.parsalableuseractivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class profileActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var string: String
    lateinit var textView: TextView

    lateinit var profileImage: ShapeableImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val person_name = findViewById<TextView>(R.id.text_person_name)
        val person_email = findViewById<TextView>(R.id.text_person_email)
        val person_age = findViewById<TextView>(R.id.text_person_age)
        val person_phone = findViewById<TextView>(R.id.text_person_phone)
        profileImage = findViewById<ShapeableImageView>(R.id.imageView)
        val user =
            intent.getParcelableExtra<Parcelable>("EXTRA_USER")
                    as com.example.parsalableuseractivity.User
        person_name.text = user.name.toString()
        person_email.text = user.email.toString()
        person_age.text = user.age.toString()
        person_phone.text = user.phoneNumber.toString()

        profileImage.setImageURI(Uri.parse(user.profilePictureUri))

        person_email.setOnClickListener {
            composeEmail(arrayOf(user.email).toString())
        }

        person_phone.setOnClickListener {

            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${person_phone.text}")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }


        }

    }

    fun composeEmail(arrayOf: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {

            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL,arrayOf)

        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}