package com.surli.surli_shinta_mvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.surli.surli_shinta_mvvm.data.MainRepository
import com.surli.surli_shinta_mvvm.data.adapter.MainAdapter
import com.surli.surli_shinta_mvvm.data.dialog.CustomLoadingDialog
import com.surli.surli_shinta_mvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var loadingUI: CustomLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory(MainRepository(this))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        loadingUI = CustomLoadingDialog(this)



        binding.btnRefresh.setOnClickListener {
            setupObserver()
        }
        setupObserver()
    }

    private fun setupObserver() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchAndLoadDogs()
        }
        viewModel.databaseDog.observe(this) {
            binding.recyclerDogs.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = MainAdapter(it)
            }
        }
    }

    override fun showLoading() {
        runOnUiThread {
            loadingUI.show()
        }

    }

    override fun hideLoading() {
        runOnUiThread {
            loadingUI.dismiss()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}