package keddit.com.egn.keddit.ui.adapter.model

import keddit.com.egn.keddit.ui.adapter.commons.ViewType

open class BaseItem : ViewType {
    override fun getViewType(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    var groupBy: String? = ""
}