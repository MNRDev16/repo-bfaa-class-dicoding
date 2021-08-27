package com.mnrdev.android.submissionbfaa1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var userDataList = ArrayList<User>()

    fun setAllData(data: List<User>) {
        userDataList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageUser : ImageView = itemView.findViewById(R.id.image_user)
        var txtUserId : TextView = itemView.findViewById(R.id.tv_user_id)
        var txtUsername : TextView = itemView.findViewById(R.id.tv_user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_cardview,parent,false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = userDataList.get(position)

        holder.apply {
            txtUserId.text = user.userID
            txtUsername.text = user.userName
        }
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(100,100))
            .into(holder.imageUser)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USER, user)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userDataList.size
    }

}