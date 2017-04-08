package team.ixigo.hack.com.team1.model.response;

import java.util.ArrayList;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public class RecommendedListResponse extends BaseResponse
{
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data
    {
        private ArrayList<RecommendedItems> flight;
        private ArrayList<RecommendedItems> budget_flight;

        public ArrayList<RecommendedItems> getFlight() {
            return flight;
        }

        public void setFlight(ArrayList<RecommendedItems> flight) {
            this.flight = flight;
        }

        public ArrayList<RecommendedItems> getBudget_flight() {
            return budget_flight;
        }

        public void setBudget_flight(ArrayList<RecommendedItems> budget_flight) {
            this.budget_flight = budget_flight;
        }

        public static class RecommendedItems
        {
            private String image;
            private String name;
            private String countryName;
            private String text;
            private String type;
            private String cityName;
            private String stateName;
            private String price;
            private String [] destinationCategories;
            private String cityId;

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountryName() {
                return countryName;
            }

            public void setCountryName(String countryName) {
                this.countryName = countryName;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getStateName() {
                return stateName;
            }

            public void setStateName(String stateName) {
                this.stateName = stateName;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String[] getDestinationCategories() {
                return destinationCategories;
            }

            public void setDestinationCategories(String[] destinationCategories) {
                this.destinationCategories = destinationCategories;
            }
        }
    }
}
