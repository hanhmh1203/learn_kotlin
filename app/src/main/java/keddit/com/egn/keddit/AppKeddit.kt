package keddit.com.egn.keddit

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import keddit.com.egn.keddit.commons.LogI
import keddit.com.egn.keddit.di.AppComponent
import keddit.com.egn.keddit.di.DaggerAppComponent
import keddit.com.egn.keddit.ui.worker.RestAPI
import javax.inject.Inject

class AppKeddit : dagger.android.support.DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out dagger.android.support.DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
    //    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//
//    }

//    @Inject
//    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

//    override fun activityInjector(): AndroidInjector<Activity> {
//        return activityDispatchingAndroidInjector
//    }

    //    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.builder().build()
//        appComponent.inject(this)

//        DaggerAppComponent.builder()
//                .application(this)
//                .build()
//                .inject(this)
    }


}