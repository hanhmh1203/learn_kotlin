package keddit.com.egn.keddit.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import keddit.com.egn.keddit.commons.LogI

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
open class RxBaseFragment : Fragment() {
    protected var compositeDisposable = CompositeDisposable()
    override fun onResume() {
        super.onResume()
        "onResume".LogI()
//        compositeDisposable = CompositeDisposable()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        "onViewCreated".LogI()
    }

    override fun onPause() {
        super.onPause()
        "onPause".LogI()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        compositeDisposable.clear()
    }
}