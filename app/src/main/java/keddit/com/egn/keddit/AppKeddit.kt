package keddit.com.egn.keddit

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import keddit.com.egn.keddit.commons.LogI
import keddit.com.egn.keddit.di.AppComponent
import keddit.com.egn.keddit.di.DaggerAppComponent
import keddit.com.egn.keddit.ui.worker.RestAPI
import javax.inject.Inject

class AppKeddit : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }


}