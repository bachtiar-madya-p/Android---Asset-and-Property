package id.app.io_asset_v01.utils;

import id.app.io_asset_v01.service.ApiService;

public class ApiUtils {

    private static final String IP_SERVER = "http://128.199.213.155";
    private static final String BASE_URL_API = IP_SERVER + ":1337/api/";
    private static final String IMG_DIRECTORY = IP_SERVER + "/io/img/";

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);
    }

    public static String getDirServer(){
        return IMG_DIRECTORY;

    }
}
