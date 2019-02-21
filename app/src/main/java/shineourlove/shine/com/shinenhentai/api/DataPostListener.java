package shineourlove.shine.com.shinenhentai.api;

import org.jsoup.nodes.Document;

/**
 * created by shineourlove on 2019/2/21
 */
public interface DataPostListener {
    void onSuccess(Document elements);

    void onError(String e);
}
