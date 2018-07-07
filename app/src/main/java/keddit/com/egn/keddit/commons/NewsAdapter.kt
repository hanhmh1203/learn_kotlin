package keddit.com.egn.keddit.commons

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstrants.LOADING
    }

    init {
        items = ArrayList()
        delegateAdapters.put(AdapterConstrants.LOADING, LoadingDelegateAdapter())
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(p1).onCreateViewHolder(p0)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        delegateAdapters.get(getItemViewType(p1)).onBindViewHolder(p0, this.items[p1])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }
}