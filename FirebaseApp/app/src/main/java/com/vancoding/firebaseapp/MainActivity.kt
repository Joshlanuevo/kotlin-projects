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
import com.google.firebase.database.getValue
import com.google.firebase.firestore.firestore

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

        val textView: TextView = findViewById(R.id.textView);

        // Initialize firestore
        val db = Firebase.firestore

        // Creating a Collection: "User"
        val users_collection = db.collection("Users")

        // Creating Documents:
        // Document one
        val user1 = hashMapOf(
            "name" to "jack",
            "lname" to "reacher",
            "born" to "1992"
        )

        // Document two
        val user2 = hashMapOf(
            "name" to "john",
            "lname" to "travolta",
            "born" to "1992"
        )

        // Adding Documents to Collection
        users_collection.document("user1").set(user1)
        users_collection.document("user2").set(user2)

        // Receive Documents from Firestore
        val docRef = db.collection("Users").document("user1")

        // Getting specific data from Document
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                textView.text = "${document.data}"
            }
        }




//     // Firebase section ↓ ↓ ↓

//        val textView: TextView = findViewById(R.id.textView);
//
//        // Real Time Database Reference
//        // https://fir-kotlin-e57f6-default-rtdb.firebaseio.com/
//        database = Firebase.database.reference
//
//        // Writing custom objects to firebase
//        val user1 = User("Ivan", "1234");
//        database.child("Users").setValue(user1);
//
//        // Reading custom objects to firebase
//        val pe = object :ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val u1 = snapshot.getValue<User>()
//                textView.text = u1.toString();
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        }
//
//        database.child("Users").addValueEventListener(pe)


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