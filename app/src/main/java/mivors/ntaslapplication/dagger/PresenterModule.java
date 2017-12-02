package mivors.ntaslapplication.dagger;




import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mivors.ntaslapplication.screens.details.DetailsPresenter;
import mivors.ntaslapplication.screens.main.MainPresenter;

/**
 * Created by Ahmed shaban on 7/30/2017.
 */
//this class created to put  providers for Presenters

@Module
public class PresenterModule {


    @Provides
    @Singleton
    MainPresenter provideMainPresenter(Context context) {
        return new MainPresenter(context);
    }


    @Provides
    @Singleton
    DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }



}

