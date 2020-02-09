package com.example.swivltestassigment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swivltestassigment.data.model.UserModel
import com.example.swivltestassigment.databinding.ItemHomeListBinding
import com.squareup.picasso.Picasso

class HomeAdapter(private val listener: onItemClickListener) :
    PagedListAdapter<UserModel, HomeAdapter.ViewHolder>(
        diffCallbak
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(View.OnClickListener {
                listener.onItemClick(item)
            }, item)
        }
    }

    companion object {
        var diffCallbak: DiffUtil.ItemCallback<UserModel> =
            object : DiffUtil.ItemCallback<UserModel>() {
                override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return true;
                }

                override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return true;
                }

            }
    }

    class ViewHolder(private val binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: UserModel) {
            binding.apply {
                clickListener = listener
                model = item
                Picasso.get().load(item.avatarUrl).into(ciUserPhoto)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(userModel: UserModel)
    }
}