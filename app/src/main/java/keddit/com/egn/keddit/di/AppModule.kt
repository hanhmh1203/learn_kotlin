package keddit.com.egn.keddit.di

import dagger.Module
import dagger.Provides
import keddit.com.egn.keddit.AppKeddit
import keddit.com.egn.keddit.ui.worker.NewsManager
import keddit.com.egn.keddit.ui.worker.RestAPI
import javax.inject.Singleton

@Module
class AppModule(val app: AppKeddit) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideRestApi() = RestAPI()

    @Provides
    @Singleton
    fun provideNewsManager() = NewsManager()
}