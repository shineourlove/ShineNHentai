package shineourlove.shine.com.shinenhentai.api;

import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.List;

/**
 * created by shineourlove on 2019/2/21
 */
public class BookModel {
    private String title;
    private List<String> tag;
    private String id;
    private String mediaId;
    private String thumbnail;
    private String cover;

    public BookModel(Element gallery) {
        String temp;
        String tags = gallery.attr("data-tags").replace(' ', ',');
        String[] t = tags.split("[,]");
        tag = Arrays.asList(t);
        Element a = gallery.getElementsByTag("a").first();
        temp = a.attr("href");
        id = temp.substring(3, temp.length() - 1);
        a = gallery.getElementsByTag("img").first();
        temp = a.hasAttr("data-src") ? a.attr("data-src") : a.attr("src");
        cover = temp;
        mediaId = temp.substring(temp.indexOf("galleries") + 10, temp.lastIndexOf('/'));
        thumbnail = temp.substring(temp.length() - 3);
        title = gallery.getElementsByTag("div").first().text();
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
