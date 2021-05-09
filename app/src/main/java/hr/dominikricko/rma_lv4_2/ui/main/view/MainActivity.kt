package hr.dominikricko.rma_lv4_2.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hr.dominikricko.rma_lv4_2.data.api.ApiHelper
import hr.dominikricko.rma_lv4_2.data.api.ApiServiceImpl
import hr.dominikricko.rma_lv4_2.data.model.User
import hr.dominikricko.rma_lv4_2.data.repository.MainRepository
import hr.dominikricko.rma_lv4_2.databinding.ActivityMainBinding
import hr.dominikricko.rma_lv4_2.di.viewModelModule
import hr.dominikricko.rma_lv4_2.ui.main.adapter.MainAdapter
import hr.dominikricko.rma_lv4_2.ui.main.viewmodel.MainViewModel
import hr.dominikricko.rma_lv4_2.utils.Status

class MainActivity : AppCompatActivity() {

    //koin doesn't work for some reason
    private val mainViewModel = MainViewModel(MainRepository(ApiHelper(ApiServiceImpl())))
    private lateinit var adapter: MainAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(ArrayList())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, {
            when (it.status) {

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}
