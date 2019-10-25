package com.example.githubKotlin.feature.repositoryK

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubKotlin.R
import com.example.githubKotlin.model.BaseK
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.proxy_screen.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryActivity : AppCompatActivity(), LifecycleOwner {

    private val adapter: RepositoryKAdapter by inject()
    private val viewModel: RepositoryKViewModel by viewModel()

    /*
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(RepositoryKViewModel::class.java)
    }
   */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        showProxyScreen(true)
        initUI()
        initObservables()
    }

    private fun initUI() {
        recyclerViewRepository.layoutManager = LinearLayoutManager(this)
        recyclerViewRepository.adapter = adapter
    }


    private fun initObservables() {

        viewModel.getPosts().observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.getBase().observe(this, Observer {
            when (it?.status) {
                BaseK.STATUS.LOADING -> {
                    Log.i("ESTADO", "LOADING")
                    showProxyScreen(true)
                }
                BaseK.STATUS.SUCCESS -> {
                    Log.i("ESTADO", "SUCESSO")
                    showProxyScreen(false)
                    showRecyclerView(true)
                }
            }
        })
    }


    private fun showProxyScreen(status: Boolean) {
        if (status)
            fl_proxy_screen.visibility = View.VISIBLE
        else
            fl_proxy_screen.visibility = View.GONE
    }

    private fun showRecyclerView(status: Boolean) {
        if (status)
            recyclerViewRepository.visibility = View.VISIBLE
        else
            recyclerViewRepository.visibility = View.GONE
    }
}
