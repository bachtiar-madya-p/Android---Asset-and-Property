package id.app.io_asset_v01.response;

public class ServerResponse {

    private int code;

    private String message;

    public ServerResponse() {
    }

    public ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
