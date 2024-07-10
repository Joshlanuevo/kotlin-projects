package com.vancoding.firebaseapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Real Time Database Reference
        // https://fir-kotlin-e57f6-default-rtdb.firebaseio.com/
        database = Firebase.database.reference

        // Writing custom objects to firebase
        val user1 = User("Ivan", "1234");
        database.child("Users").setValue(user1);



//        val textView: TextView = findViewById(R.id.textView);
//
//        // Real Time Database Reference
//        // https://fir-kotlin-e57f6-default-rtdb.firebaseio.com/
//        database = Firebase.database.reference
//
//        // Write Data to Firebase
//        database.child("price").setValue("1940 PHP")
//
//        // Reading Data from firebase
//        val postListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val gold_price = snapshot.value;
//                textView.text = gold_price.toString();
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        }
//
//        database.child("price").addValueEventListener(postListener);
    }
}