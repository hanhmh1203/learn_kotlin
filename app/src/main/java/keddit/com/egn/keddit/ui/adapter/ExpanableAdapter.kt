package keddit.com.egn.keddit.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import keddit.com.egn.keddit.commons.*
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.commons.ViewTypeDelegateAdapter
import keddit.com.egn.keddit.ui.adapter.model.BaseItem
import keddit.com.egn.keddit.ui.adapter.model.HeaderItem
import keddit.com.egn.keddit.ui.adapter.model.LoadingItem
import kotlin.collections.ArrayList

class ExpanableAdapter(val clickListener: (ViewType) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ExpanableInterface {
    /**
     * for expanable list
     */
    override fun expandable(headerItem: HeaderItem) {
        var positionHeader = items.indexOf(headerItem)
        var listChild = headerItem.listChild
        if (listChild != null) {
            this.items.addAll(positionHeader + 1, (listChild))
            notifyItemRangeInserted(positionHeader + 1, listChild.size)
            headerItem.listChild.clear()
        }
    }

    /**
     * for unexpanable list
     */
    override fun unExpandable(headerItem: HeaderItem): List<ViewType> {
        var positionHeader = items.indexOf(headerItem)
        var itemsRemove = items.filter {
            (it as BaseItem).groupBy!!.contentEquals(StringBuilder(headerItem.groupBy))
        }
        (itemsRemove as ArrayList).remove(headerItem)
        items.removeAll(itemsRemove)
        this.notifyItemRangeRemoved(positionHeader + 1, itemsRemove.size)
        return itemsRemove
    }

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = LoadingItem

    fun addNews(news: List<ViewType>) {
        //remove loading item first
        if (news[0] is HeaderItem) {
            var header = news[0] as HeaderItem
            addHeader(news[0])
            (news as ArrayList).remove(header)
            if (!header.isExpanable) {
                header.listChild.addAll(news)
                news.clear()
            }
        }
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition) // change at position remove

        items.addAll(news)
        var posFrom = items.size - news.size - 1
        items.add(loadingItem)

        notifyItemRangeInserted(posFrom, items.size  /* plus loading item */)
    }

    fun addHeader(new: ViewType) {
        //remove loading item first
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition) // change at position remove
        if (items == null) {
            items = ArrayList()
        }
        items.add(new)
        if (items.size == 1) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(items.size - 1)
        }
        items.add(loadingItem)
        notifyItemInserted(items.size)
    }

    fun clearAndAddNews(news: List<ViewType>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<ViewType> {
        return items.filter { it.getViewType() == AdapterConstrants.NEWS || it.getViewType() == AdapterConstrants.HEADER }
                .map { it as ViewType }

    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    init {
        items = ArrayList()
        delegateAdapters.put(AdapterConstrants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstrants.NEWS, NewsDelegateAdapter())
        delegateAdapters.put(AdapterConstrants.HEADER, HeaderDelegateAdapter())
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(position).onCreateViewHolder(viewGroup, this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(viewHolder, this.items[position], position, clickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }
}