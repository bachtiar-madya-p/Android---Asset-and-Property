package id.app.io_asset_v01.response;

public class UserSchema {

    private String username;
    private String memberName;
    private String role;
    private String memberCode;
    private String email;
    private String image;
    private String level;
    private String department;

    public UserSchema() {
    }

    public UserSchema(String username, String memberName, String role, String memberCode, String email, String image, String level, String department) {
        this.username = username;
        this.memberName = memberName;
        this.role = role;
        this.memberCode = memberCode;
        this.email = email;
        this.image = image;
        this.level = level;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
