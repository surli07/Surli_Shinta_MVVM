package com.surli.surli_shinta_mvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.surli.surli_shinta_mvvm.data.adapter.MainAdapter
import com.surli.surli_shinta_mvvm.data.dialog.CustomLoadingDialog
import com.surli.surli_shinta_mvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRefresh.setOnClickListener {
            getDog()
        }
        setupObserver()
    }

    private fun getDog() {
        lifecycleScope.launch {
            viewModel.fetchAndLoadDogs()
        }
    }

    private fun setupObserver() {
        getDog()

        viewModel.dog.observe(this) {
            binding.recyclerDogs.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = MainAdapter(it)
            }
        }
        viewModel.message.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        val loadingUI = CustomLoadingDialog(this)
        viewModel.loading.observe(this) {
            if (it) {
                loadingUI.show()
            } else {
                loadingUI.hide()
            }
        }
    }


}