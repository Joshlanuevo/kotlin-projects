package com.vancoding.contactlistapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vancoding.contactlistapp.R
import com.vancoding.contactlistapp.bean.UsersBean
import com.vancoding.contactlistapp.ui.UserInfoActivity

class UsersAdapter(private val users: List<UsersBean>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val email: TextView = view.findViewById(R.id.userEmail)
        val name: TextView = view.findViewById(R.id.userName)
        val avatar: ImageView = view.findViewById(R.id.userAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.email.text = user.email
        holder.name.text = "${user.first_name} ${user.last_name}"
        Glide.with(holder.avatar.context)
            .load(user.avatar)
            .apply(RequestOptions.circleCropTransform()) // Apply circular crop
            .into(holder.avatar)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UserInfoActivity::class.java).apply {
                putExtra("email", user.email)
                putExtra("first_name", user.first_name)
                putExtra("last_name", user.last_name)
                putExtra("avatar", user.avatar)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = users.size
}
