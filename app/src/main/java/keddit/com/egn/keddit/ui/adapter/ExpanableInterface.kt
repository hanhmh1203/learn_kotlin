package keddit.com.egn.keddit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.model.HeaderItem

interface ExpanableInterface {
    fun expandable(headerItem: HeaderItem)
    fun unExpandable(headerItem: HeaderItem): List<ViewType>
}