package practice.module.com.hackathon.networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCar {
    @SerializedName("carname")
    @Expose
    private String carname;
    @SerializedName("carseats")
    @Expose
    private Integer carseats;
    @SerializedName("carNumber")
    @Expose
    private String carNumber;

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public Integer getCarseats() {
        return carseats;
    }

    public void setCarseats(Integer carseats) {
        this.carseats = carseats;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
