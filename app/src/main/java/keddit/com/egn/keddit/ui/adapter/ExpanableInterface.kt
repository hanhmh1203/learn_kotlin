package keddit.com.egn.keddit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import keddit.com.egn.keddit.ui.adapter.commons.ViewType

interface ExpanableInterface {
    fun ex(positionHeader: Int, items: List<ViewType>? = null)
    fun unEx(positionHeader: Int): List<ViewType>
}