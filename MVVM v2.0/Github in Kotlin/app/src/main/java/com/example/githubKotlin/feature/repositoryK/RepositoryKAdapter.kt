package com.example.githubKotlin.feature.repositoryK

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.githubKotlin.R
import com.example.githubKotlin.model.RepositoryK
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryKAdapter :
    PagedListAdapter<RepositoryK, RepositoryKAdapter.RepositoryKAdapterViewHolder>(diffCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryKAdapterViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)

        return RepositoryKAdapterViewHolder(
            view
        )
    }


    override fun onBindViewHolder(holder: RepositoryKAdapterViewHolder, position: Int) {
        val repository = getItem(position)
        repository?.let { holder.bindView(it) }
    }


    class RepositoryKAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun bindView(repository: RepositoryK) {

            //Glide Configuration
            val requestOptions =
                RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).circleCrop()

            //Put on the informations in the views
            itemView.tvRepository.text = repository.name
            itemView.tvRepositoryDescription.text = repository.description
            Glide.with(itemView.context)
                .asBitmap()
                .load(repository.owner.avatar_url)
                .apply(requestOptions)
                .into(itemView.imageViewRepository)
            itemView.tvUsername.text = repository.owner.login
            itemView.tvForkRepository.text = repository.forks_count.toString()
            itemView.tvStarRepository.text = repository.stargazers_count.toString()


            ViewCompat.setAccessibilityDelegate(
                itemView.tvUsername,
                object : AccessibilityDelegateCompat() {
                    override fun onInitializeAccessibilityNodeInfo(
                        host: View?,
                        info: AccessibilityNodeInfoCompat?
                    ) {
                        host?.contentDescription = "Teste de metodo"
                        super.onInitializeAccessibilityNodeInfo(host, info)
                    }
                })
        }


        override fun onClick(p0: View?) {

        }

    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RepositoryK>() {
            override fun areItemsTheSame(oldItem: RepositoryK, newItem: RepositoryK): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RepositoryK, newItem: RepositoryK): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

}