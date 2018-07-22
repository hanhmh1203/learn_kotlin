package keddit.com.egn.keddit.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import keddit.com.egn.keddit.ui.FirstFragment

/**
 * Created by Mai Huu Hanh on 7/22/18.
 */
@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): FirstFragment
}