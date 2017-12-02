package mivors.ntaslapplication.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mivors.ntaslapplication.sqlLite.ItemDbHelper;

/**
 * Created by Ahmed shaban on 7/30/2017.
 */
//this class created to put main providers an all application
@Module
public class AppModule {
    private Application application;


    //provide  application reference
    public AppModule(Application application) {
        this.application = application;
    }


    //provide  context
    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }


    //provide  item helper for sqllit
    @Provides
    @Singleton
    public ItemDbHelper provideItemHelper(Context context) {
        return new ItemDbHelper(context);
    }







}
