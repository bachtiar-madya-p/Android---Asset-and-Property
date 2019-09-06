package id.app.io_asset_v01.response;

public class AssetResponse {
     private String code;
     private String name;
     private String type;
     private String manufacture;
     private String model;
     private String vendor;
     private String note;
     private String register_date;

    public AssetResponse() {
    }

    public AssetResponse(String code, String name, String type, String manufacture, String model, String vendor, String note, String register_date) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.manufacture = manufacture;
        this.model = model;
        this.vendor = vendor;
        this.note = note;
        this.register_date = register_date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }
}
