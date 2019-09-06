package id.app.io_asset_v01.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.app.io_asset_v01.R;
import id.app.io_asset_v01.request.AssetRequest;
import id.app.io_asset_v01.response.AssetResponse;
import id.app.io_asset_v01.service.ApiService;
import id.app.io_asset_v01.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDetailsActivity extends AppCompatActivity {

    private TextView txtAssetCode, txtAssetName, txtAssetType, txtManufacture, txtModel,
            txtVendor, txtNote, txtCreatedDate;
    private Button btnRegister;
    private String assetCode;

    Context context;
    ApiService api;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assetdetails);
        bindView();
        getAssetDetails();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AssetRegistrationActivity.class);
                intent.putExtra("assetCode", assetCode);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AssetDetailsActivity.this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    private void bindView() {
        txtAssetCode = findViewById(R.id.detail_assetCode);
        txtAssetName = findViewById(R.id.detail_assetName);
        txtAssetType = findViewById(R.id.detail_assetType);
        txtManufacture = findViewById(R.id.detail_manufacture);
        txtModel = findViewById(R.id.detail_model);
        txtVendor = findViewById(R.id.detail_vendor);
        txtNote = findViewById(R.id.detail_note);
        txtCreatedDate = findViewById(R.id.detail_createdDate);
        btnRegister = findViewById(R.id.detail_btn_register);

        context = this;
        api = ApiUtils.getAPIService();
    }
    private void getAssetDetails() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            assetCode = bundle.getString("assetCode");
        }
        AssetRequest request = new AssetRequest(assetCode);
        loading = ProgressDialog.show(context, null, "Getting asset details ...", false, false);
        Call<List<AssetResponse>> call = api.getAssetDetails(request);
        call.enqueue(new Callback<List<AssetResponse>>() {
            @Override
            public void onResponse(Call<List<AssetResponse>> call, Response<List<AssetResponse>> response) {
                if (response.isSuccessful()){
                    List<AssetResponse> list = response.body();
                    txtAssetCode.setText(list.get(0).getCode());
                    txtAssetName.setText(list.get(0).getName());
                    txtAssetType.setText(list.get(0).getType());
                    txtManufacture.setText(list.get(0).getManufacture());
                    txtModel.setText(list.get(0).getModel());
                    txtVendor.setText(list.get(0).getVendor());
                    txtNote.setText(list.get(0).getNote());
                    txtCreatedDate.setText(list.get(0).getRegister_date());
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String message = jsonObject.getString("message");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<List<AssetResponse>> call, Throwable t) {
                Toast.makeText(context, "ERROR : " + t.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
