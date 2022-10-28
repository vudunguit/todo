package io.tcn.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.tcn.todo.databinding.ActivityMainBinding
import io.tcn.todo.ui.buyList.BuyListActivity
import io.tcn.todo.ui.callList.CallListActivity
import io.tcn.todo.ui.sellList.SellListActivity

class MainActivity: AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnCallList.setOnClickListener {
      CallListActivity.start(this)
    }
    binding.btnBuyList.setOnClickListener {
      BuyListActivity.start(this)
    }
    binding.btnSellList.setOnClickListener {
      SellListActivity.start(this)
    }
  }
}