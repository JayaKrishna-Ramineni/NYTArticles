package com.dmss.nytnews.Model.Pojo;

import com.dmss.nytnews.Model.Common;

import org.json.JSONObject;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NewsMediaMetaDataModel  implements Serializable {
    String mediaUrl,format;
    int height,width;
    public NewsMediaMetaDataModel(String mediaResult) {
        try {
            JSONObject mediaJsonObject = new JSONObject(mediaResult);
            mediaUrl = mediaJsonObject.isNull(Common.imageUrl)?"":mediaJsonObject.getString(Common.imageUrl);
            format = mediaJsonObject.isNull(Common.format)?"":mediaJsonObject.getString(Common.format);

            width = mediaJsonObject.isNull(Common.imageWidth)?0:mediaJsonObject.getInt(Common.imageWidth);
            height = mediaJsonObject.isNull(Common.imageHeight)?0:mediaJsonObject.getInt(Common.imageHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
