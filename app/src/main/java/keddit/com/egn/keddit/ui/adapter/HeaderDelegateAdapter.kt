package keddit.com.egn.keddit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import keddit.com.egn.keddit.R
import keddit.com.egn.keddit.commons.*
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.commons.ViewTypeDelegateAdapter
import keddit.com.egn.keddit.ui.adapter.model.HeaderItem
import kotlinx.android.synthetic.main.header.view.*

class HeaderDelegateAdapter : ViewTypeDelegateAdapter {

    lateinit var adapter: ExpanableInterface

    override fun onCreateViewHolder(parent: ViewGroup, expanableInterface: ExpanableInterface): RecyclerView.ViewHolder {
        adapter = expanableInterface
        return TurnsViewHolder(parent, adapter)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, position: Int, clickListener: (ViewType) -> Unit) {
        holder as TurnsViewHolder
        holder.bind(item as HeaderItem, position)
    }

    class TurnsViewHolder(parent: ViewGroup, adapter: ExpanableInterface) : RecyclerView.ViewHolder(parent.inflate(R.layout.header)) {
        val adapter = adapter

        fun bind(item: HeaderItem, position: Int) = with(itemView) {
            tvLeft.text = item.left
            tvRight.text = item.right
            tvCenter.text = item.center
            itemView.setOnClickListener {
                expanable(adapter, item, position)
            }
        }

        private fun expanable(adapter: ExpanableInterface, item: ViewType, position: Int) {
            val headerItem = item as HeaderItem
            if (headerItem.isExpanable) {
                headerItem.listChild = adapter.unExpandable(headerItem) as ArrayList<ViewType>
            } else {
                adapter.expandable(headerItem)
            }
            headerItem.isExpanable = !headerItem.isExpanable
        }
    }

}
