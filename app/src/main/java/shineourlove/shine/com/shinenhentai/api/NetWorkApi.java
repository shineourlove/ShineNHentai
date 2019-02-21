package shineourlove.shine.com.shinenhentai.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import shineourlove.shine.com.shinenhentai.utils.ShineUtils;

/**
 * created by shineourlove on 2019/2/21
 */
public class NetWorkApi {
    private static NetWorkApi mInstance;
    private static Retrofit retrofit;
    public ApiService apiService;

    public static NetWorkApi getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkApi.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkApi();
                }
            }
        }
        return mInstance;
    }

    private NetWorkApi() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                ShineUtils.logDebug("ok_http_log", "log: " + message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiService.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }
}
