package id.app.io_asset_v01.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import id.app.io_asset_v01.R;
import id.app.io_asset_v01.utils.SessionManager;

public class AssetRegistrationActivity extends AppCompatActivity {
    private EditText input_assetCode, input_locationName, input_memberCode, input_buildingName,
            input_note, txtLatitude, txtLongitude, txtNote;
    private String assetCode, memberCode, txtBuildingNameValue, txtMemberCodeValue,
            txtRateValue, txtLatitudeValue, txtLongitudeValue, txtNoteValue;

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assetregister);
        bindView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            assetCode = bundle.getString("assetCode");
        }
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        memberCode = user.get(SessionManager.KEY_MEMBER_CODE);

        input_assetCode.setText(assetCode);
        input_memberCode.setText(memberCode);
    }
    private void bindView()
    {
        input_assetCode = (EditText)findViewById(R.id.input_assetCode);
        input_memberCode = (EditText)findViewById(R.id.input_memberCode);
    }
    public void onBackPressed() {
        super.onBackPressed();
        AssetRegistrationActivity.this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
}
