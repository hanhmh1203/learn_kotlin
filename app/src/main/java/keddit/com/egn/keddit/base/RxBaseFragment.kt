package keddit.com.egn.keddit.base

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
open class RxBaseFragment : Fragment() {
    protected var compositeDisposable = CompositeDisposable()
    override fun onResume() {
        super.onResume()
        compositeDisposable = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        compositeDisposable.clear()
    }
}