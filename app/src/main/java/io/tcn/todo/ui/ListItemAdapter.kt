package io.tcn.todo.ui.buyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.tcn.todo.data.model.Item
import io.tcn.todo.databinding.ItemProductBinding

class ListItemAdapter : ListAdapter<Item, ListItemAdapter.BuyViewHolder>(FilterBuyItemDiff) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemProductBinding.inflate(inflater, parent, false)
    return BuyViewHolder(binding)
  }

  override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class BuyViewHolder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {
    fun bind(item: Item) {
      binding.tvName.text = "Name: ${item.name}"
      binding.tvPrice.text = "Price: ${item.price}"
      binding.tvQuantity.text = "Quantity: ${item.quantity}"
    }
  }
}

private object FilterBuyItemDiff : DiffUtil.ItemCallback<Item>() {
  override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
    return oldItem == newItem
  }
}