package team.ixigo.hack.com.team1.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public class SearchResponse1 extends BaseResponse
{
    @SerializedName("text")
    private String cityName;

    @SerializedName("url")
    private String url;

    @SerializedName("_id")
    private String cityId;

    @SerializedName("st")
    private String state;

    @SerializedName("address")
    private String address;

    @SerializedName("co")
    private String country;

    @SerializedName("xid")
    private String xId;

    @SerializedName("lat")
    private float lat = 0.0f;

    @SerializedName("lon")
    public float lon = 0.0f;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getxId() {
        return xId;
    }

    public void setxId(String xId) {
        this.xId = xId;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
