package mivors.ntaslapplication.baseClass;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

// this class created to be base for each presenter in all application
public interface BasePresenter<T>  {
    // empty function for attach view
    void onAttach(T view);
    // empty function for Detach view
    void onDetach();
}
