package biriinfotech.com.doglover.model;

import java.io.Serializable;

/**
 * Created by Biri Infotech on 2/7/2018.
 */

public class MenuModel implements Serializable {

//    {"id":"1",
//     "indexx":"1",
//     "title":"Home",
//     "description":"Lorem ipsum dolor ",
//     "image":"1516887821imgpsh_fullsize4.png",
//     "category_id":"1"}

    private  String id;
    private  String indexx;
    private  String title;
    private  String description;
    private  String image;
    private  String category_id;
    private  String video_url;
    private  String pdf_link;
    private  String web_view_url;
    private int position;

    public String getWeb_view_url() {
        return web_view_url;
    }

    public void setWeb_view_url(String web_view_url) {
        this.web_view_url = web_view_url;
    }

    public String getPdf_link() {
        return pdf_link;
    }

    public void setPdf_link(String pdf_link) {
        this.pdf_link = pdf_link;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndexx() {
        return indexx;
    }

    public void setIndexx(String indexx) {
        this.indexx = indexx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
