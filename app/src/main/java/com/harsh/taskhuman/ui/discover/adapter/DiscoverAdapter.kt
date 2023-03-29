package com.harsh.taskhuman.ui.discover.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.util.gone
import com.harsh.taskhuman.common.util.loadUserProfile
import com.harsh.taskhuman.common.util.visible
import com.harsh.taskhuman.databinding.ItemHeaderBinding
import com.harsh.taskhuman.databinding.ItemSkillsBinding
import com.harsh.taskhuman.ui.discover.model.Skill
import javax.inject.Inject

class DiscoverAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER_VIEW = 0
        private const val NORMAL_VIEW = 1
    }

    var discoverList = mutableListOf<Skill>()

    fun updateDiscoverList(discoverList: List<Skill>) {
        val oldListLength = this.discoverList.size
        this.discoverList.addAll(discoverList)
        notifyItemRangeInserted(oldListLength, discoverList.size)
    }

    fun clearAll() {
        this.discoverList.clear()
        notifyDataSetChanged()
    }

    var onItemClicked: (Skill) -> Unit = {}

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
            skill: Skill,
            onItemClicked: (Skill) -> Unit,
        ) {
            with(binding) {
                binding.itemTitle.text = skill.displayTileName

                binding.ivAvailability.setBackgroundColor(
                    Color.parseColor(
                        skill.availability?.color ?: "#B5B5B5"
                    )
                )

                val userDetailsList = skill.providerInfo ?: mutableListOf()

                if (userDetailsList.isNotEmpty()) {
                    image1.loadUserProfile(userDetailsList[0].profileImage)
                    image1.visible()
                } else {
                    image1.gone()
                }

                if (userDetailsList.size > 1) {
                    image2.loadUserProfile(userDetailsList[1].profileImage)
                    image2.visible()
                } else {
                    image2.gone()
                }

                if (userDetailsList.size > 2) {
                    image3.loadUserProfile(userDetailsList[2].profileImage)
                    image3.visible()
                } else {
                    image3.gone()
                }

                if (userDetailsList.size > 3) {
                    image4.loadUserProfile(userDetailsList[3].profileImage)
                    image4.visible()
                } else {
                    image4.gone()
                }

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