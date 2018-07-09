package keddit.com.egn.keddit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import keddit.com.egn.keddit.R
import keddit.com.egn.keddit.commons.*
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.commons.ViewTypeDelegateAdapter
import keddit.com.egn.keddit.ui.adapter.model.HeaderItem
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsItem
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.news_item.view.*

class HeaderDelegateAdapter : ViewTypeDelegateAdapter {

    //    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
//        return TurnsViewHolder(parent)
//    }
    lateinit var adapter: ExpanableInterface
    var itemsChild: List<ViewType> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, expanableInterface: ExpanableInterface): RecyclerView.ViewHolder {
        adapter = expanableInterface
        return TurnsViewHolder(parent, adapter)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, position: Int) {
        holder as TurnsViewHolder
        holder.bind(item as HeaderItem, position)
    }

    class TurnsViewHolder(parent: ViewGroup, adapter: ExpanableInterface) : RecyclerView.ViewHolder(parent.inflate(R.layout.header)) {
        var itemsChild: List<ViewType> = emptyList()
        val adapter = adapter
        fun bind(item: HeaderItem, position: Int) = with(itemView) {
            tvLeft.setText(item.left)
            tvRight.setText(item.right)
            tvCenter.setText(item.center)
            tvCenter.setOnClickListener(View.OnClickListener {
                expanable(adapter, item, position)
            })
        }

        private fun expanable(adapter: ExpanableInterface, item: ViewType, position: Int) {
            val headerItem = item as HeaderItem
            if (headerItem.isExpanable) {
                itemsChild = adapter.unEx(position)
            } else {
                adapter.ex(position,itemsChild)
            }
            headerItem.isExpanable = !headerItem.isExpanable
        }
    }

}
