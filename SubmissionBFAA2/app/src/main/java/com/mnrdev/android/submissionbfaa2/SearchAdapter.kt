package com.mnrdev.android.submissionbfaa2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SearchAdapter(private val list: List<ItemsItem>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val imgUser : ImageView = itemView.findViewById(R.id.image_user)
        val userName : TextView = itemView.findViewById(R.id.tv_user_name)
        val userType : TextView = itemView.findViewById(R.id.tv_user_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_user,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val user = list.get(position)

        holder.apply {
            userName.text = user.login
            userType.text = user.type
        }
        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .apply(RequestOptions().override(100,100))
            .into(holder.imgUser)

        /*holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USER, user)
            holder.itemView.context.startActivity(intent)
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }
}