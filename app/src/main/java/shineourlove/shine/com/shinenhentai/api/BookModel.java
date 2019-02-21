package shineourlove.shine.com.shinenhentai.api;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * created by shineourlove on 2019/2/21
 */
public class BookModel {
    private String title;
    private List<String> tag;
    private String cover;
    private String id;
    private String mediaId;
    private String thumbnail;

    public BookModel(Element gallery) {
        String temp;
        String tags = gallery.attr("data-tags").replace(' ', ',');
        Element a = gallery.getElementsByTag("a").first();
        temp = a.attr("href");
        id = temp.substring(3, temp.length() - 1);
        a = gallery.getElementsByTag("img").first();
        temp = a.hasAttr("data-src") ? a.attr("data-src") : a.attr("src");
        mediaId = temp.substring(temp.indexOf("galleries") + 10, temp.lastIndexOf('/'));
        thumbnail = temp.substring(temp.length() - 3);
        title = gallery.getElementsByTag("div").first().text();
    }
}
