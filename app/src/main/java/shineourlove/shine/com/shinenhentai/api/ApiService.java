package shineourlove.shine.com.shinenhentai.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * created by shineourlove on 2019/2/21
 */
public interface ApiService {
    String HOST = "https://nhentai.net/";

    /**
     * 通用post
     */
    @GET
    Observable<ResponseBody> getData(@Url String url);
}
