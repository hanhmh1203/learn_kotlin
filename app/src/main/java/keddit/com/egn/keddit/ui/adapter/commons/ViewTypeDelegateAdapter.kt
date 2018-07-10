package keddit.com.egn.keddit.ui.adapter.commons

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import keddit.com.egn.keddit.ui.adapter.ExpanableInterface

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup, expanableInterface: ExpanableInterface): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                         item: ViewType, position: Int,
                         clickListener: (ViewType) -> Unit)

}