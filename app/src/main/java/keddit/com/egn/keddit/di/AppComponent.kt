package keddit.com.egn.keddit.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import keddit.com.egn.keddit.AppKeddit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class))
interface AppComponent : AndroidInjector<AppKeddit> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppKeddit>()

//    @Component.Builder
//    interface Builder {
//        @BindsInstance fun application(app: AppKeddit): Builder
//        fun build(): AppComponent
//    }
//    fun inject(appKeddit: AppKeddit)
}