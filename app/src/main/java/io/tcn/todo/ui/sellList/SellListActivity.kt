package io.tcn.todo.ui.sellList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.tcn.todo.R
import io.tcn.todo.databinding.ActivityListBinding
import io.tcn.todo.ui.buyList.BuyListActivity
import io.tcn.todo.ui.buyList.BuyListViewModel
import io.tcn.todo.ui.buyList.ListItemAdapter

@AndroidEntryPoint
class SellListActivity: AppCompatActivity() {
  private lateinit var binding: ActivityListBinding
  private val viewModel: SellListViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityListBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)
    binding.toolbar.setNavigationOnClickListener { finish() }
    binding.toolbar.title = getString(R.string.sell_list)

    val callAdapter = ListItemAdapter()
    binding.rvList.apply {
      layoutManager = LinearLayoutManager(this@SellListActivity)
      adapter = callAdapter
    }
    binding.srlLayout.setOnRefreshListener {
      viewModel.refresh()
    }
    viewModel.loading.observe(this){
      binding.srlLayout.isRefreshing = it
    }
    viewModel.sellList.observe(this) {
      callAdapter.submitList(it)
    }
    viewModel.message.observe(this) { msg ->
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
  }

  companion object{
    fun start(context: Context){
      context.startActivity(Intent(context, SellListActivity::class.java))
    }
  }
}