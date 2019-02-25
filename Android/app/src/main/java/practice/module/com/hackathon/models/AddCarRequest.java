package practice.module.com.hackathon.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCarRequest {

        @SerializedName("carname")
        @Expose
        public String carname;
        @SerializedName("carseats")
        @Expose
        public Integer carseats;
        @SerializedName("addedBy")
        @Expose
        public Integer addedBy;
        @SerializedName("carNumber")
        @Expose
        public String carNumber;

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

        public Integer getAddedBy() {
                return addedBy;
        }

        public void setAddedBy(Integer addedBy) {
                this.addedBy = addedBy;
        }

        public String getCarNumber() {
                return carNumber;
        }

        public void setCarNumber(String carNumber) {
                this.carNumber = carNumber;
        }

    }

