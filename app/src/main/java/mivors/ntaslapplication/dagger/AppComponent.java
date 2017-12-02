package mivors.ntaslapplication.dagger;


import javax.inject.Singleton;

import dagger.Component;
import mivors.ntaslapplication.screens.details.DetailsActivity;
import mivors.ntaslapplication.screens.main.MainActivity;
import mivors.ntaslapplication.screens.main.MainPresenter;

/**
 * Created by Ahmed shaban on 7/30/2017.
 */
// this class created for register who need inject
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,PresenterModule.class})
public interface AppComponent {
    //register main activity it will need objects for injection
    void inject(MainActivity mainActivity);

    //register MainPresenter it will need objects for injection
    void inject(MainPresenter mainPresenter);

    //register DetailsActivity it will need objects for injection
    void inject(DetailsActivity detailsActivity);
}
