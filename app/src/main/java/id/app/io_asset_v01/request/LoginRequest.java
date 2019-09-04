package id.app.io_asset_v01.request;

public class LoginRequest {

    private String uname;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasword() {
        return password;
    }

    public void setPassword(String pasword) {
        this.password = pasword;
    }
}
