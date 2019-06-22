package com.dmss.nytnews.Model.Pojo;

import com.dmss.nytnews.Model.Common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NewsModel implements Serializable {
    String url, keywords, column, section, byline, type, title, articleAbstract, publishedDate, source, uri;
    int views, id, assetId;
    ArrayList<String> desFacet = new ArrayList<>(),
            orgFacet = new ArrayList<>(),
            perFacet = new ArrayList<>(),
            geoFacet = new ArrayList<>();
    ArrayList<NewsMediaModel> mediaModels = new ArrayList<NewsMediaModel>();

    public NewsModel(String result) {

        try {

            JSONObject articleJsonObject = new JSONObject(result);
            url = articleJsonObject.isNull(Common.articleUrl) ? "" : articleJsonObject.getString(Common.articleUrl);
            keywords = articleJsonObject.isNull(Common.keywords) ? "" : articleJsonObject.getString(Common.keywords);
            column = articleJsonObject.isNull(Common.column) ? "" : articleJsonObject.getString(Common.column);
            section = articleJsonObject.isNull(Common.section) ? "" : articleJsonObject.getString(Common.section);
            byline = articleJsonObject.isNull(Common.byLine) ? "" : articleJsonObject.getString(Common.byLine);
            type = articleJsonObject.isNull(Common.articleType) ? "" : articleJsonObject.getString(Common.articleType);
            title = articleJsonObject.isNull(Common.title) ? "" : articleJsonObject.getString(Common.title);
            articleAbstract = articleJsonObject.isNull(Common.articleAbstract) ? "" : articleJsonObject.getString(Common.articleAbstract);
            publishedDate = articleJsonObject.isNull(Common.publishedDate) ? "" : articleJsonObject.getString(Common.publishedDate);
            source = articleJsonObject.isNull(Common.source) ? "" : articleJsonObject.getString(Common.source);

            views = articleJsonObject.isNull(Common.views) ? 0 : articleJsonObject.getInt(Common.views);

            id = articleJsonObject.isNull(Common.id) ? 0 : articleJsonObject.getInt(Common.id);
            assetId = articleJsonObject.isNull(Common.assetId) ? 0 : articleJsonObject.getInt(Common.assetId);

            if (!articleJsonObject.isNull(Common.orgFacet) && articleJsonObject.get(Common.orgFacet).toString().length() > 0 && articleJsonObject.getJSONArray(Common.orgFacet).length() > 0) {
                JSONArray jsonArray = articleJsonObject.getJSONArray(Common.orgFacet);
                for (int i = 0; i < jsonArray.length(); i++) {
                    orgFacet.add(jsonArray.getString(i));
                }
            }

            if (!articleJsonObject.isNull(Common.perFacet) && articleJsonObject.get(Common.perFacet).toString().length() > 0 && articleJsonObject.getJSONArray(Common.perFacet).length() > 0) {
                JSONArray jsonArray = articleJsonObject.getJSONArray(Common.perFacet);
                for (int i = 0; i < jsonArray.length(); i++) {
                    perFacet.add(jsonArray.getString(i));
                }
            }

            if (!articleJsonObject.isNull(Common.geoFacet) && articleJsonObject.get(Common.geoFacet).toString().length() > 0 && articleJsonObject.getJSONArray(Common.geoFacet).length() > 0) {
                JSONArray jsonArray = articleJsonObject.getJSONArray(Common.geoFacet);
                for (int i = 0; i < jsonArray.length(); i++) {
                    geoFacet.add(jsonArray.getString(i));
                }
            }

            if (!articleJsonObject.isNull(Common.desFacet) && articleJsonObject.get(Common.desFacet).toString().length() > 0 && articleJsonObject.getJSONArray(Common.desFacet).length() > 0) {
                JSONArray jsonArray = articleJsonObject.getJSONArray(Common.desFacet);
                for (int i = 0; i < jsonArray.length(); i++) {
                    desFacet.add(jsonArray.getString(i));
                }
            }

            if (!articleJsonObject.isNull(Common.media) && articleJsonObject.get(Common.media).toString().length() > 0 && articleJsonObject.getJSONArray(Common.media).length() > 0) {
                JSONArray jsonArray = articleJsonObject.getJSONArray(Common.media);
                for (int i = 0; i < jsonArray.length(); i++) {
                    NewsMediaModel mediaModel = new NewsMediaModel(jsonArray.getJSONObject(i).toString());
                    mediaModels.add(mediaModel);
                }
            }


            uri = articleJsonObject.isNull(Common.articleUri) ? "" : articleJsonObject.getString(Common.articleUri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public ArrayList<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(ArrayList<String> desFacet) {
        this.desFacet = desFacet;
    }

    public ArrayList<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(ArrayList<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public ArrayList<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(ArrayList<String> perFacet) {
        this.perFacet = perFacet;
    }

    public ArrayList<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(ArrayList<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public ArrayList<NewsMediaModel> getMediaModels() {
        return mediaModels;
    }

    public void setMediaModels(ArrayList<NewsMediaModel> mediaModels) {
        this.mediaModels = mediaModels;
    }
}
