package id.app.io_asset_v01.request;

public class AssetRequest {
    public String assetCode;

    public AssetRequest() {
    }

    public AssetRequest(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
}
