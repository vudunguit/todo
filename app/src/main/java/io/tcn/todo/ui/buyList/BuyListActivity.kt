package io.tcn.todo.ui.buyList

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

@AndroidEntryPoint
class BuyListActivity: AppCompatActivity() {

  private lateinit var binding: ActivityListBinding
  private val viewModel: BuyListViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityListBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)
    binding.toolbar.setNavigationOnClickListener { finish() }
    binding.toolbar.title = getString(R.string.buy_list)

    val callAdapter = ListItemAdapter()
    binding.rvList.apply {
      layoutManager = LinearLayoutManager(this@BuyListActivity)
      adapter = callAdapter
    }
    binding.srlLayout.setOnRefreshListener {
      viewModel.refresh()
    }
    viewModel.loading.observe(this){
      binding.srlLayout.isRefreshing = it
    }
    viewModel.buyList.observe(this) {
      callAdapter.submitList(it)
    }
    viewModel.message.observe(this) { msg ->
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
  }

  companion object{
    fun start(context: Context){
      context.startActivity(Intent(context, BuyListActivity::class.java))
    }
  }
}