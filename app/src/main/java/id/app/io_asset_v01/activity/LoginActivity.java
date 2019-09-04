package id.app.io_asset_v01.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.app.io_asset_v01.MainActivity;
import id.app.io_asset_v01.R;
import id.app.io_asset_v01.request.LoginRequest;
import id.app.io_asset_v01.response.ServerResponse;
import id.app.io_asset_v01.response.UserSchema;
import id.app.io_asset_v01.service.ApiService;
import id.app.io_asset_v01.utils.ApiUtils;
import id.app.io_asset_v01.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText inpUname, inpPasswrd;
    Button btnLogin;

    Context mContext;
    ApiService mApiService;
    ProgressDialog loading;
    SessionManager session;
    private List<UserSchema> userSchemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin(inpUname.getText().toString(), inpPasswrd.getText().toString())) {
                    doLogin(inpUname.getText().toString(), inpPasswrd.getText().toString());
                }
            }
        });

    }

    private void bindView() {
        inpUname = (EditText) findViewById(R.id.login_emailid);
        inpPasswrd = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.loginBtn);
        mContext = this;
        mApiService = ApiUtils.getAPIService();
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            session.checkLogin();
            navigatetoHomeActivity();
        }
    }

    private void navigatetoHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private boolean validateLogin(String uname, String password) {
        if (uname == null || uname.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String uname, final String password) {

        LoginRequest request = new LoginRequest(uname, password);
        loading = ProgressDialog.show(mContext, null, "Logging in ...", true, false);
        Call<ServerResponse> call = mApiService.login(request);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    getUserDetails(uname, password);
                    session.createLoginSession(uname);

                } else {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.errorBody().string());
                        String message = jsonRESULTS.getString("message");
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });

    }

    private void getUserDetails(String uname, String password) {

        LoginRequest request = new LoginRequest(uname, password);
        loading = ProgressDialog.show(mContext, null, "Getting information ...", true, false);

        Call<List<UserSchema>> call = mApiService.getUserDetails(request);
        call.enqueue(new Callback<List<UserSchema>>() {
            @Override
            public void onResponse(Call<List<UserSchema>> call, Response<List<UserSchema>> response) {
                if (response.isSuccessful()) {

                        //UserSchema schema = new UserSchema();
                        userSchemas = response.body();
                        //schema = userSchemas.get(0);
                        String username = userSchemas.get(0).getUsername();
                        String alias = userSchemas.get(0).getAlias();
                        String role = userSchemas.get(0).getRole();
                        String memberCode = userSchemas.get(0).getMemberCode();
                        String email = userSchemas.get(0).getEmail();
                        String image = userSchemas.get(0).getImage();
                        String level = userSchemas.get(0).getLevel();
                        String department = userSchemas.get(0).getDepartment();

                        session.createLoginSession(username, alias, role, memberCode, email, image, level, department);

                        if(session.isLoggedIn())
                        {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }

                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<List<UserSchema>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
