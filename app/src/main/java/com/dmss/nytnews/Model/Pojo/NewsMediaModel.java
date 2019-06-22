package com.dmss.nytnews.Model.Pojo;

import com.dmss.nytnews.Model.Common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class NewsMediaModel  implements Serializable {
    String mediaType,mediaSubType,caption,copyRight;
    int approvedBySyndication;
    ArrayList<NewsMediaMetaDataModel> metaDataModels = new ArrayList<NewsMediaMetaDataModel>();
    public NewsMediaModel(String mediaResult) {
        try {
            JSONObject mediaJsonObject = new JSONObject(mediaResult);
            mediaType = mediaJsonObject.isNull(Common.mediaType)?"":mediaJsonObject.getString(Common.mediaType);
            mediaSubType = mediaJsonObject.isNull(Common.subtype)?"":mediaJsonObject.getString(Common.subtype);
            caption = mediaJsonObject.isNull(Common.caption)?"":mediaJsonObject.getString(Common.caption);
            copyRight = mediaJsonObject.isNull(Common.copyRight)?"":mediaJsonObject.getString(Common.copyRight);
            approvedBySyndication = mediaJsonObject.isNull(Common.approvedForSyndication)?0:mediaJsonObject.getInt(Common.approvedForSyndication);

            if(!mediaJsonObject.isNull(Common.mediaMetaData) && mediaJsonObject.get(Common.mediaMetaData).toString().length()> 0 && mediaJsonObject.getJSONArray(Common.mediaMetaData).length() > 0){
                JSONArray jsonArray = mediaJsonObject.getJSONArray(Common.mediaMetaData);
                for (int i = 0;i<jsonArray.length();i++){
                    NewsMediaMetaDataModel metaDataModel = new NewsMediaMetaDataModel(jsonArray.getJSONObject(i).toString());
                    metaDataModels.add(metaDataModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaSubType() {
        return mediaSubType;
    }

    public void setMediaSubType(String mediaSubType) {
        this.mediaSubType = mediaSubType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public int getApprovedBySyndication() {
        return approvedBySyndication;
    }

    public void setApprovedBySyndication(int approvedBySyndication) {
        this.approvedBySyndication = approvedBySyndication;
    }

    public ArrayList<NewsMediaMetaDataModel> getMetaDataModels() {
        return metaDataModels;
    }

    public void setMetaDataModels(ArrayList<NewsMediaMetaDataModel> metaDataModels) {
        this.metaDataModels = metaDataModels;
    }
}
