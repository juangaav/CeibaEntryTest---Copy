package com.example.ceibaentrytest.viewmodel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaentrytest.databinding.RecyclerviewItemBinding
import com.example.ceibaentrytest.model.User
import java.util.Locale

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>(), Filterable {
    var users = mutableListOf<User>()
    var filteredUserList = mutableListOf<User>()

    init {
        filteredUserList = users as ArrayList<User>
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val user = users[position]
        holder.binding.userNameTextView.text = user.name
        holder.binding.userTelephoneTextView.text = user.phone
        holder.binding.userEmailTextView.text = user.email
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filteredUserList = if (charSearch.isEmpty()) {
                    users as ArrayList<User>
                } else {
                    val resultList = mutableListOf<User>()
                    for (row in users) {
                        if (row.name?.contains(charSearch.lowercase(Locale.ROOT)) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredUserList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredUserList = results?.values as MutableList<User>
                notifyDataSetChanged()
            }
        }
    }
}

class HomeViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {}