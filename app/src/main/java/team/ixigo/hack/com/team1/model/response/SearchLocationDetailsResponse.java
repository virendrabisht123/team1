package team.ixigo.hack.com.team1.model.response;

import java.util.ArrayList;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public class SearchLocationDetailsResponse extends BaseResponse
{
    private SearchDetails data;

    public SearchDetails getData() {
        return data;
    }

    public void setData(SearchDetails data) {
        this.data = data;
    }

    public static class SearchDetails
    {
        private ArrayList<String> categoryNames;
        private String countryName;
        private String description;
        private String xid;
        private String keyImageUrl;
        private String whyToVisit;
        private String latitude;
        private String longitude;
        private String name;
        private String stateName;
        private String shortDescription;
        private String id;

        public ArrayList<String> getCategoryNames() {
            return categoryNames;
        }

        public void setCategoryNames(ArrayList<String> categoryNames) {
            this.categoryNames = categoryNames;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getXid() {
            return xid;
        }

        public void setXid(String xid) {
            this.xid = xid;
        }

        public String getKeyImageUrl() {
            return keyImageUrl;
        }

        public void setKeyImageUrl(String keyImageUrl) {
            this.keyImageUrl = keyImageUrl;
        }

        public String getWhyToVisit() {
            return whyToVisit;
        }

        public void setWhyToVisit(String whyToVisit) {
            this.whyToVisit = whyToVisit;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
