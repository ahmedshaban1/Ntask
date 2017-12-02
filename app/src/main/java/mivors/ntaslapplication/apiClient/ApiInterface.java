package mivors.ntaslapplication.apiClient;

import mivors.ntaslapplication.model.item.GetAllItemsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

// this class creating for register all endpoints implementation

public interface ApiInterface {
    @GET(EndPoints.GET_ALL_ITEMS)
    Observable<GetAllItemsResponse> getAllItems();

}
