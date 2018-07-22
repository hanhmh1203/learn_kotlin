package keddit.com.egn.keddit.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import keddit.com.egn.keddit.AppKeddit
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(appKeddit: AppKeddit)
}