package keddit.com.egn.keddit.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import keddit.com.egn.keddit.AppKeddit
import keddit.com.egn.keddit.di.fragment.FragmentBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class))
interface AppComponent : AndroidInjector<AppKeddit> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppKeddit>()
}