package com.vancoding.contactsmanagerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vancoding.contactsmanagerapp.bean.ContactBean
import com.vancoding.contactsmanagerapp.databinding.ActivityMainBinding
import com.vancoding.contactsmanagerapp.db.ContactDb
import com.vancoding.contactsmanagerapp.repository.ContactRepository
import com.vancoding.contactsmanagerapp.view.MyRecyclerViewAdapter
import com.vancoding.contactsmanagerapp.viewmodel.ContactViewModel
import com.vancoding.contactsmanagerapp.viewmodel.ContactViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var contactViewModel: ContactViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // ROOM Database
        val dao = ContactDb.getInstance(applicationContext).contactDao;
        val repository = ContactRepository(dao);
        val factory = ContactViewModelFactory(repository);

        // View Model
        contactViewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java);

        binding.contactViewModel = contactViewModel;

        // use this: LiveData and Data Binding Integration
        binding.lifecycleOwner = this;

        initReclycerView();
    }

    private fun initReclycerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this);
        DisplayUsersList();
    }

    private fun DisplayUsersList() {
        contactViewModel.contacts.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, { selectedItem: ContactBean -> listItemClicked(selectedItem) }
            )
        })
    }

    private fun listItemClicked(selectedItem: ContactBean) {
        Toast.makeText(this, "Selected name is ${selectedItem.name}", Toast.LENGTH_LONG).show();
        contactViewModel.initUpdateAndDelete(selectedItem);
    }
}
