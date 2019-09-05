package id.app.io_asset_v01.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.util.HashMap;
import de.hdodenhof.circleimageview.CircleImageView;
import id.app.io_asset_v01.R;
import id.app.io_asset_v01.utils.SessionManager;

public class ProfileActivity extends AppCompatActivity {


    TextView profileName, profileMemberCode, profileDepartment, profileEmail, profileLevel, profileRole;
    SessionManager session;
    String imgUrl, userName, userCode, userDepartment, userEmail, userLevel, userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        imgUrl = user.get(SessionManager.KEY_IMAGE);
        userName = user.get(SessionManager.KEY_MEMBERNAME);
        userCode = user.get(SessionManager.KEY_MEMBER_CODE);
        userDepartment = user.get(SessionManager.KEY_DEPARTMENT);
        userEmail = user.get(SessionManager.KEY_EMAIL);
        userLevel = user.get(SessionManager.KEY_LEVEL);
        userRole = user.get(SessionManager.KEY_ROLE);

        profileName = (TextView) findViewById(R.id.profileName);
        profileMemberCode = (TextView) findViewById(R.id.profileMemberCode);
        profileDepartment = (TextView) findViewById(R.id.profileDepartment);
        profileEmail = (TextView) findViewById(R.id.profileEmail);
        profileLevel = (TextView) findViewById(R.id.profileLevel);
        profileRole = (TextView) findViewById(R.id.profileRole);

        profileName.setText(userName);
        profileMemberCode.setText(userCode);
        profileDepartment.setText(userDepartment);
        profileLevel.setText(userLevel);
        profileEmail.setText(userEmail);
        profileRole.setText(userRole);

        new ProfileActivity.DownloadImageFromInternet((CircleImageView) findViewById(R.id.profileImage)).execute(imgUrl);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ProfileActivity.this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        CircleImageView profileImage;

        public DownloadImageFromInternet(CircleImageView imageView) {
            this.profileImage = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            profileImage.setImageBitmap(result);
        }
    }
}
