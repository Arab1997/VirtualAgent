package myway.virtualagent.models.manfacturer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Manufacturer {
    @Expose
    @SerializedName("results")
    private List<Results> results;
    @Expose
    @SerializedName("count")
    private int count;


    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class Results {
        @Expose
        @SerializedName("slug")
        private String slug;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("logo")
        private String logo;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }

}
