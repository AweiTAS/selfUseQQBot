package my.mirai.pojo;

import java.util.Arrays;
import java.util.Map;

public class LoliconData {
    long pid;
    int p;
    long uid;
    String title;
    String author;
    boolean r18;
    int width;
    int height;
    String[] tags;
    String ext;
    long uploadDate;
    Map<String, String> urls;

    public LoliconData() {
    }

    @Override
    public String toString() {
        return "LoliconData{" +
                "pid=" + pid +
                ", p=" + p +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", r18=" + r18 +
                ", width=" + width +
                ", height=" + height +
                ", tags=" + Arrays.toString(tags) +
                ", ext='" + ext + '\'' +
                ", uploadDate=" + uploadDate +
                ", urls=" + urls +
                '}';
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isR18() {
        return r18;
    }

    public void setR18(boolean r18) {
        this.r18 = r18;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }
}
