package id.app.io_asset_v01.utils;

import id.app.io_asset_v01.service.ApiService;

public class ApiUtils {
    private static final String BASE_URL_API = "http://128.199.213.155:1337/api/";

    // Mendeklarasikan Interface BaseApiService

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);
    }
}
