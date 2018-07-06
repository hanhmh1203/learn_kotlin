package keddit.com.egn.keddit.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Mai Huu Hanh on 7/6/18.
 */
fun ViewGroup.inflate(layoutId: Int, attactToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}