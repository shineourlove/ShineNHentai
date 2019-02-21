package shineourlove.shine.com.shinenhentai.api;

import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import shineourlove.shine.com.shinenhentai.utils.ShineUtils;

/**
 * created by shineourlove on 2019/2/21
 */
public class BookApi {
    private Observer<String> bookObserver;
    private OkHttpClient client;

    private BookApi() {
    }

    private static class SingletonInstance {
        private static final BookApi INSTANCE = new BookApi();
    }

    public static BookApi getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public void getBook(final String url) {
        if (bookObserver == null) {
            bookObserver = new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {
                    requestBook(s);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            };
        }
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(url);
            }
        });
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(bookObserver);
    }

    private void requestBook(String url) {
        if (client == null) {
            client = new OkHttpClient();
        }
        client.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    Document d = Jsoup.parse(response.body().byteStream(), "UTF-8", "https://nhentai.net");
                    Elements scripts = d.getElementsByTag("script");
                    Elements gallery = d.getElementsByClass("gallery");

                    if (gallery.first() != null) {
                        ShineUtils.logDebug("data_log", "first: " + gallery.first().html());
                        String tags = gallery.first().attr("data-tags").replace(' ', ',');
                        ShineUtils.logDebug("data_log", "tags: " + tags);
                    }

                    if (scripts.last() == null) {
                        return;
                    }
                    String str = scripts.last().html();
                    int s = str.indexOf("new N.gallery(") + 14, s1 = str.indexOf('\n', s) - 2;
                    if (s == 13 || s1 < 0) return;
                    str = str.substring(s, s1);
                }
            }
        });
    }
}
