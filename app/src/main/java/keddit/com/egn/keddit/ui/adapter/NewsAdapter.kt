package keddit.com.egn.keddit.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import keddit.com.egn.keddit.commons.*
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.commons.ViewTypeDelegateAdapter
import keddit.com.egn.keddit.ui.adapter.model.LoadingItem
import kotlin.collections.ArrayList

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ExpanableInterface {
    override fun ex(positionHeader: Int, items: List<ViewType>?) {
        "NewsAdapter positionHeader $positionHeader".LogI()
        if (items != null) {
            this.items.addAll(positionHeader + 1, (items))
            notifyItemRangeInserted(positionHeader+1, items.size)
        }
    }

    override fun unEx(positionHeader: Int): List<ViewType> {
        "NewsAdapter unex positionHeader $positionHeader".LogE()
        var itemsRemove: ArrayList<ViewType>
        itemsRemove = ArrayList()
        var posLast = getLastPosition()
        for (i in positionHeader + 1..positionHeader + 10) {
            itemsRemove.add(items[i])
        }
        items.removeAll(itemsRemove)
        this.notifyItemRangeRemoved(positionHeader + 1, itemsRemove.size)
        return itemsRemove
    }
//    override fun unEx(positionHeader: Int): List<ViewType> {
//        "NewsAdapter unex positionHeader $positionHeader".LogE()
//        var itemsRemove: ArrayList<ViewType>
//        itemsRemove = ArrayList()
//        var posLast = getLastPosition()
//        for (i in positionHeader + 1..items.size - 1) {
//            itemsRemove.add(items[i])
//        }
//        items.removeAll(itemsRemove)
//        var lastPosition = (items.size - 1) + (itemsRemove.size - 1) as Int
//        "size items before $posLast, itemsremove ${itemsRemove.size}, items after ${items.size}, lastposition $lastPosition".LogI()
//            this.notifyItemRangeRemoved(positionHeader + 1, itemsRemove.size)
//        return itemsRemove
//    }

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = LoadingItem

    fun addNews(news: List<ViewType>) {
        //remove loading item first
//        val initPosition = items.size - 1
//        items.removeAt(initPosition)
//        notifyItemRemoved(initPosition) // change at position remove

        items.addAll(news)
        var posFrom = items.size - news.size-1
        var posTo = items.size+1
        notifyItemRangeChanged(posFrom, posTo)
//        notifyDataSetChanged()
//        items.add(loadingItem)
//        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun addHeader(new: ViewType) {
        //remove loading item first
//        val initPosition = items.size - 1
//        items.removeAt(initPosition)
//        notifyItemRemoved(initPosition) // change at position remove
        if (items == null) {
            items = ArrayList()
        }
        items.add(new)
//        notifyDataSetChanged()
        if (items.size == 1) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(items.size - 1)
        }
//        items.add(loadingItem)
//        notifyItemChanged(items.size + 1)
//        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddNews(news: List<ViewType>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())
        items.addAll(news)
//        items.add(loadingItem)
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
//        items.add(loadingItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(position).onCreateViewHolder(viewGroup, this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(viewHolder, this.items[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }
}