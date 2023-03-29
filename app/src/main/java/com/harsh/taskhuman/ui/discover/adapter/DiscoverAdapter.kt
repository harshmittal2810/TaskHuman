package com.harsh.taskhuman.ui.discover.adapter

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.util.gone
import com.harsh.taskhuman.common.util.isValidAndNotEmpty
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

    var skillsList = mutableListOf<Skill>()

    fun updateDiscoverList(list: List<Skill>) {
        val oldListLength = skillsList.size
        skillsList.addAll(list)
        notifyItemRangeInserted(oldListLength, list.size)
    }

    fun clearAll() {
        skillsList.clear()
        notifyDataSetChanged()
    }

    var onAddRemoveClicked: (position: Int, model: Skill, isFav: Boolean) -> Unit = { _, _, _ -> }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                if (skillsList.isNotEmpty()) {
                    holder.onBind(
                        position - 1,
                        skillsList[position - 1],
                        onAddRemoveClicked,
                    )
                }
            }

            is ItemHeaderViewHolder -> {
                holder.onBind(holder.itemView.context.getString(R.string.grow_your_leadership))
            }
        }
    }

    override fun getItemCount(): Int {
        return skillsList.size + 1
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
            position: Int,
            skill: Skill,
            onAddRemoveClicked: (position: Int, model: Skill, isFav: Boolean) -> Unit,
        ) {
            with(binding) {
                binding.itemTitle.text = if (skill.displayTileName.isValidAndNotEmpty()) {
                    skill.displayTileName
                } else {
                    skill.tileName
                }

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

                if (skill.isFavorite == true) {
                    ivFav.setImageResource(R.drawable.ic_fav_remove)
                    tvFav.text = root.context.getString(R.string.remove_favorite)
                } else {
                    ivFav.setImageResource(R.drawable.ic_fav)
                    tvFav.text = root.context.getString(R.string.add_favorite)
                }

                if (skill.showSidePanel) {
                    swipeLayout.openEndMenu(true)
                    if (skill.isFavorite == true) {
                        tvFav.text = root.context.getString(R.string.added)
                    } else {
                        tvFav.text = root.context.getString(R.string.removed)
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        swipeLayout.closeEndMenu(true)
                        if (skill.isFavorite == true) {
                            tvFav.text = root.context.getString(R.string.remove_favorite)
                        } else {
                            tvFav.text = root.context.getString(R.string.add_favorite)
                        }
                    }, 3 * 1000)
                }

                endMenu.setOnClickListener {
                    onAddRemoveClicked(position, skill, !(skill.isFavorite ?: false))
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

    fun itemUpdated(position: Int, model: Skill) {
        skillsList.forEachIndexed { index, skill ->
            skill.showSidePanel = position == index
        }
//        notifyItemChanged(position + 1)
        notifyDataSetChanged()
    }

    class ItemHeaderViewHolder(private val itemHeaderBinding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(itemHeaderBinding.root) {
        fun onBind(headerTitle: String) {
            itemHeaderBinding.tvHeader.text = headerTitle
        }
    }
}