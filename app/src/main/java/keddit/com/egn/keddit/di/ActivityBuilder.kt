package keddit.com.egn.keddit.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import keddit.com.egn.keddit.MainActivity
import keddit.com.egn.keddit.di.a.MainActivityModule

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}
