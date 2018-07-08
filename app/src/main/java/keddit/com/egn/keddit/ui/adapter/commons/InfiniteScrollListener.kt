package keddit.com.egn.keddit.ui.adapter.commons

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import keddit.com.egn.keddit.commons.LogI

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: LinearLayoutManager)
    : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            visibleItemCount = recyclerView.childCount;
            totalItemCount = layoutManager.itemCount;
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
            ("loading $loading, totalItemCount $totalItemCount, visibleItemCount $visibleItemCount" +
                    "firstVisibleItem $firstVisibleItem, visibleThreshold $visibleThreshold, previousTotal $previousTotal").LogI()
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                "End reached".LogI("InfiniteScrollListener")
                func()
                loading = true;
            } else {
//                "else".LogI()
//                ("loading $loading, totalItemCount $totalItemCount, visibleItemCount $visibleItemCount" +
//                        "firstVisibleItem $firstVisibleItem, visibleThreshold $visibleThreshold").LogI()
            }
        }
    }

}