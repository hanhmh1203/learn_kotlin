package keddit.com.egn.keddit.di.a

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import keddit.com.egn.keddit.MainActivity
import keddit.com.egn.keddit.ui.worker.NewsManager
import keddit.com.egn.keddit.ui.worker.RestAPI
import javax.inject.Singleton

@Module
class MainActivityModule(val mainActivity: MainActivity) {
    @Provides
    @Singleton
    fun provideMainActivity() = mainActivity

}