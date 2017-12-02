package mivors.ntaslapplication.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mivors.ntaslapplication.apiClient.ApiInterface;
import mivors.ntaslapplication.apiClient.EndPoints;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


//this class created to put  providers for retrofit library

@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    // get end point url
    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return EndPoints.BASE_URL;
    }


    // set converter provider for retrofit
    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }



    // this an Interceptor for adding  headers and standard params of requests

    @Provides
    @Singleton
    OkHttpClient provideOkHTTp(Interceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

   //creating singleton retrofit object
    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(NAME_BASE_URL) String baseUrl, Converter.Factory converter) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    //creating singleton ApiInterface object to call end points

    @Provides
    @Singleton
    ApiInterface provideUsdaApi(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }



}
