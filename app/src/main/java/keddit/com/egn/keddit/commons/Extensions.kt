package keddit.com.egn.keddit.commons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Mai Huu Hanh on 7/6/18.
 */
fun ViewGroup.inflate(layoutId: Int, attactToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}