package id.app.io_asset_v01.service;

import java.util.List;

import id.app.io_asset_v01.request.AssetRequest;
import id.app.io_asset_v01.request.LoginRequest;
import id.app.io_asset_v01.response.AssetResponse;
import id.app.io_asset_v01.response.ServerResponse;
import id.app.io_asset_v01.response.UserSchema;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("login/mobile")
    Call<ServerResponse> login(@Body LoginRequest input);

    @Headers("Content-Type: application/json")
    @POST("mobile/userdetails")
    Call<List<UserSchema>>getUserDetails(@Body LoginRequest input);

    @Headers("Content-Type: application/json")
    @POST("mobile/asset")
    Call<List<AssetResponse>>getAssetDetails(@Body AssetRequest input);

}
