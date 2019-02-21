package shineourlove.shine.com.shinenhentai.api.contract;

import org.jsoup.select.Elements;

import shineourlove.shine.com.shinenhentai.api.DataPostListener;

/**
 * created by shineourlove on 2019/2/21
 */
public interface BaseGetContract {
    interface BaseGetModel {
        void getData(String url, DataPostListener listener);
    }

    interface BaseGetPresenter {
        void getData(String url);
    }
}
