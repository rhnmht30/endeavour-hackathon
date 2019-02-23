package practice.module.com.hackathon.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("uniq_id")
    @Expose
    private Integer uniqId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUniqId() {
        return uniqId;
    }

    public void setUniqId(Integer uniqId) {
        this.uniqId = uniqId;
    }

}