package keddit.com.egn.keddit.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import keddit.com.egn.keddit.commons.*
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.commons.ViewTypeDelegateAdapter
import keddit.com.egn.keddit.ui.adapter.model.HeaderItem
import keddit.com.egn.keddit.ui.adapter.model.LoadingItem
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsItem
import kotlin.collections.ArrayList

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = LoadingItem

    fun addNews(news: List<ViewType>) {
        //remove loading item first
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition) // change at position remove
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun addHeader(new: ViewType) {
        //remove loading item first
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition) // change at position remove
        items.add(new)
        notifyItemChanged(items.size)
        items.add(loadingItem)
        notifyItemChanged(items.size + 1)
//        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddNews(news: List<ViewType>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<ViewType> {
        return items.filter { it.getViewType() == AdapterConstrants.NEWS || it.getViewType()==AdapterConstrants.HEADER }
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
        return delegateAdapters.get(position).onCreateViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(viewHolder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }
}