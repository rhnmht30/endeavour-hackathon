package practice.module.com.hackathon.networking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getData {
    @SerializedName("userdata")
    @Expose
    private getUserData userdata;
    @SerializedName("car")
    @Expose
    private getCar car;

    public getUserData getUserdata() {
        return userdata;
    }

    public void setUserdata(getUserData userdata) {
        this.userdata = userdata;
    }

    public getCar getCar() {
        return car;
    }

    public void setCar(getCar car) {
        this.car = car;
    }

}
