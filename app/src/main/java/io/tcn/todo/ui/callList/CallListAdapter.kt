package io.tcn.todo.ui.callList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.tcn.todo.data.model.CallItem
import io.tcn.todo.databinding.ItemCallBinding

class CallListAdapter : ListAdapter<CallItem, CallListAdapter.CallViewHolder>(FilterCallItemDiff) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemCallBinding.inflate(inflater, parent, false)
    return CallViewHolder(binding)
  }

  override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class CallViewHolder(private val binding: ItemCallBinding) : ViewHolder(binding.root) {
    fun bind(item: CallItem) {
      binding.tvName.text = "Name: ${item.name}"
      binding.tvNumber.text = "Number: ${item.number}"
    }
  }
}

private object FilterCallItemDiff : DiffUtil.ItemCallback<CallItem>() {
  override fun areItemsTheSame(oldItem: CallItem, newItem: CallItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CallItem, newItem: CallItem): Boolean {
    return oldItem == newItem
  }
}