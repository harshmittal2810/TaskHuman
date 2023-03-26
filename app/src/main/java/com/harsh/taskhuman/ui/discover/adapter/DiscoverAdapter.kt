package com.harsh.taskhuman.ui.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.taskhuman.R
import com.harsh.taskhuman.databinding.ItemHeaderBinding
import com.harsh.taskhuman.databinding.ItemSkillsBinding
import javax.inject.Inject

class DiscoverAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER_VIEW = 0
        private const val NORMAL_VIEW = 1
    }

    var discoverList = mutableListOf<String>()

    fun updateDiscoverList(discoverList: List<String>) {
        val oldListLength = this.discoverList.size
        this.discoverList.addAll(discoverList)
        notifyItemRangeInserted(oldListLength, discoverList.size)
    }

    fun clearAll() {
        this.discoverList.clear()
        notifyDataSetChanged()
    }

    var onItemClicked: (String) -> Unit = {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                if (discoverList.isNotEmpty()) {
                    holder.onBind(
                        discoverList[position - 1], onItemClicked
                    )
                }
            }

            is ItemHeaderViewHolder -> {
                holder.onBind(holder.itemView.context.getString(R.string.grow_your_leadership))
            }
        }
    }

    override fun getItemCount(): Int {
        return discoverList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER_VIEW
        } else {
            NORMAL_VIEW
        }
    }

    inner class ItemViewHolder(val binding: ItemSkillsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            model: String,
            onItemClicked: (String) -> Unit,
        ) {
            with(binding) {
                binding.itemTitle.text = binding.root.context.getString(R.string.app_name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            HEADER_VIEW -> {
                ItemHeaderViewHolder(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> {
                ItemViewHolder(
                    ItemSkillsBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
    }

    class ItemHeaderViewHolder(private val itemHeaderBinding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(itemHeaderBinding.root) {
        fun onBind(headerTitle: String) {
            itemHeaderBinding.tvHeader.text = headerTitle
        }
    }
}