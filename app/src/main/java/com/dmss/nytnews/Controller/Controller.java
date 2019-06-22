package com.dmss.nytnews.Controller;

import com.dmss.nytnews.Model.API.ApiServiceCalls;
import com.dmss.nytnews.Model.Common;
import com.dmss.nytnews.Model.Pojo.NewsMediaModel;
import com.dmss.nytnews.Model.Pojo.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Controller {
    ApiCallBack apiCallBack;
    ApiServiceCalls apiServiceCalls;

    public Controller(ApiCallBack apiCallBack) {
        this.apiCallBack = apiCallBack;
        apiServiceCalls = new ApiServiceCalls();
    }

    public void startFetching(int duration, int sectionType) {
        String url = "";
        switch (sectionType) {
            case Common.VIEWED_SECTION:
                url = Common.viewedSection + duration + Common.normalExtension + Common.keyExtension;
                break;
            case Common.EMAILED_SECTION:
                url = Common.emailedSection + duration + Common.normalExtension + Common.keyExtension;
                break;
            case Common.SHARED_SECTION:
                url = Common.sharedSection + duration + Common.sharedExtension + Common.keyExtension;
                break;
        }
        apiServiceCalls.getDataNewMyPlace(url, apiCallBack);

    }

    public interface ApiCallBack {
        public void onErrorApiCall(String error);

        public void onSuccessApiCall(String result);
    }

    public ArrayList<NewsModel> parseData(String result) {
        ArrayList<NewsModel> newsModels = new ArrayList<>();

        try {
            JSONObject resultJsonObject = new JSONObject(result);
            String status = resultJsonObject.isNull(Common.status) ? "" : resultJsonObject.getString(Common.status);
            if (status.equalsIgnoreCase("ok")) {
                if (!resultJsonObject.isNull(Common.results)) {
                    JSONArray resultsArray = resultJsonObject.getJSONArray(Common.results);
                    for (int i=0;i<resultsArray.length();i++){
                        NewsModel newsModel = new NewsModel(resultsArray.getJSONObject(i).toString());
                        newsModels.add(newsModel);
                    }
                    return newsModels;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
