package com.gmail.martsulgp.gympo.presentation.menu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.Holder>() {
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var profile: List<UserDataRequest> = arrayListOf()
//    private var itemCount = 0

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var userName: TextView = itemView.findViewById(R.id.profile_user_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_profile, parent, false)
        return Holder(root)
    }
}

