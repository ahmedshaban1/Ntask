package mivors.ntaslapplication.screens.details;

import mivors.ntaslapplication.baseClass.BasePresenter;
import mivors.ntaslapplication.screens.details.DetailsView;

/**
 * Created by Ahmed shaban on 12/2/2017.
 */

public class DetailsPresenter implements BasePresenter<DetailsView> {
    DetailsView mDetailsView;
    @Override
    public void onAttach(DetailsView view) {
        mDetailsView = view;
        mDetailsView.onAttache();
    }

    @Override
    public void onDetach() {
        mDetailsView  = null;
    }
}
