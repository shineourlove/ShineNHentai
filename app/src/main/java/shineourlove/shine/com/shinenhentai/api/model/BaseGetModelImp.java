package shineourlove.shine.com.shinenhentai.api.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import shineourlove.shine.com.shinenhentai.api.DataPostListener;
import shineourlove.shine.com.shinenhentai.api.NetWorkApi;
import shineourlove.shine.com.shinenhentai.api.contract.BaseGetContract;

/**
 * created by shineourlove on 2019/2/21
 */
public class BaseGetModelImp implements BaseGetContract.BaseGetModel {
    @Override
    public void getData(String url, final DataPostListener listener) {
        NetWorkApi.getInstance().apiService.getData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody response) {
                        if (response != null) {
                            try {
                                Document d = null;
                                d = Jsoup.parse(response.byteStream(), "UTF-8", "https://nhentai.net");
                                if (d == null) {
                                    listener.onError("网络连接失败");
                                } else {
                                    listener.onSuccess(d);
                                }
                            } catch (IOException e) {
                                listener.onError("网络连接失败");
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError("网络连接失败");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
