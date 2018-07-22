package keddit.com.egn.keddit.di

import dagger.Module
import dagger.Provides
import keddit.com.egn.keddit.AppKeddit
import keddit.com.egn.keddit.ui.worker.NewsManager
import keddit.com.egn.keddit.ui.worker.RestAPI
import javax.inject.Singleton

@Module
class AppModule() {
//    @Provides
//    @Singleton
//    fun provideApp(appKeddit: AppKeddit) = appKeddit

    @Provides
    @Singleton
    fun provideRestApi() = RestAPI()

    @Provides
    @Singleton
    fun provideNewsManager(restAPI: RestAPI) = NewsManager(restAPI)

//    @Provides
//    @Singleton
//    fun provideNewsManager() = NewsManager()

}