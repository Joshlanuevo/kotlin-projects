package com.vancoding.contactsmanagerapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vancoding.contactsmanagerapp.R
import com.vancoding.contactsmanagerapp.databinding.ItemCardBinding
import com.vancoding.contactsmanagerapp.bean.ContactBean

class MyRecyclerViewAdapter(
    private val contactList:List<ContactBean>,
    private val clickListener: (ContactBean) -> Unit) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

        class MyViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(contact: ContactBean, clickListener: (ContactBean) -> Unit) {

                binding.nameTextView.text = contact.name;
                binding.emailTextView.text = contact.email;

                binding.listItemLayout.setOnClickListener {
                    clickListener(contact);
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val binding: ItemCardBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_card, parent, false
        );
        return MyViewHolder(binding);
    }

    override fun getItemCount(): Int {
        return contactList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactList[position], clickListener);
    }
}