package com.dmss.nytnews.Model;

public class Common {

    protected static String apiKeyNYT = "Vup1Lsoy9cAkB4MDVXjT7h5aKG4BN8oc";
    public static String baseUrl = "https://api.nytimes.com/svc/mostpopular/v2/";
    public static String emailedSection = baseUrl + "emailed/";
    public static String viewedSection = baseUrl + "viewed/";
    public static String sharedSection = baseUrl + "shared/";
    public static String normalExtension = ".json";
    public static String sharedExtension = "/facebook.json";
    public static String keyExtension = "?api-key=" + apiKeyNYT;
    public static final int DURATION_ONE = 1, DURATION_SEVEN = 7, DURATION_THIRTY = 30,
            VIEWED_SECTION = 201, EMAILED_SECTION = 202, SHARED_SECTION = 203;

    public static String status = "status";
    public static String totalResultsCount = "num_results";
    public static String results = "results";
    public static String articleUrl = "url";
    public static String keywords = "adx_keywords";
    public static String column = "column";
    public static String section = "section";
    public static String byLine = "byline";
    public static String articleType = "type";
    public static String title = "title";
    public static String articleAbstract = "abstract";
    public static String publishedDate = "published_date";
    public static String source = "source";
    public static String id = "id";
    public static String assetId = "asset_id";
    public static String views = "views";
    public static String desFacet = "des_facet";
    public static String orgFacet = "org_facet";
    public static String perFacet = "per_facet";
    public static String geoFacet = "geo_facet";
    public static String media = "media";
    public static String mediaType = "type";
    public static String subtype = "subtype";
    public static String caption = "caption";
    public static String copyRight = "copyright";
    public static String approvedForSyndication = "approved_for_syndication";
    public static String mediaMetaData = "media-metadata";
    public static String imageUrl = "url";
    public static String format = "format";
    public static String imageHeight = "height";
    public static String imageWidth = "width";

    public static String articleUri = "uri";

}
