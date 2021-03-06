package keddit.com.egn.keddit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import keddit.com.egn.keddit.commons.LogI
import keddit.com.egn.keddit.ui.FirstFragment
import keddit.com.egn.keddit.ui.worker.NewsManager
import keddit.com.egn.keddit.ui.worker.RestAPI
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
//    @Inject lateinit var restAPI: RestAPI
//    @Inject lateinit var newsManager: NewsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            changeFragment(FirstFragment(), true)
        }
//        newsManager.text.LogI("MainActivity")
//        newsManager.action()
//        restAPI.isNotNull()
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.replace(R.id.content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
