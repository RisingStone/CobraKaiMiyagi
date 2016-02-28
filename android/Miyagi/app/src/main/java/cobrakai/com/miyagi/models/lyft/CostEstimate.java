
package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CostEstimate {

    @SerializedName("ride_type")
    @Expose
    private String rideType;
    @SerializedName("estimated_duration_seconds")
    @Expose
    private Integer estimatedDurationSeconds;
    @SerializedName("estimated_distance_miles")
    @Expose
    private Integer estimatedDistanceMiles;
    @SerializedName("estimated_cost_cents_max")
    @Expose
    private Integer estimatedCostCentsMax;
    @SerializedName("primetime_percentage")
    @Expose
    private String primetimePercentage;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("estimated_cost_cents_min")
    @Expose
    private Integer estimatedCostCentsMin;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("primetime_confirmation_token")
    @Expose
    private Object primetimeConfirmationToken;

    /**
     * 
     * @return
     *     The rideType
     */
    public String getRideType() {
        return rideType;
    }

    /**
     * 
     * @param rideType
     *     The ride_type
     */
    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    /**
     * 
     * @return
     *     The estimatedDurationSeconds
     */
    public Integer getEstimatedDurationSeconds() {
        return estimatedDurationSeconds;
    }

    /**
     * 
     * @param estimatedDurationSeconds
     *     The estimated_duration_seconds
     */
    public void setEstimatedDurationSeconds(Integer estimatedDurationSeconds) {
        this.estimatedDurationSeconds = estimatedDurationSeconds;
    }

    /**
     * 
     * @return
     *     The estimatedDistanceMiles
     */
    public Integer getEstimatedDistanceMiles() {
        return estimatedDistanceMiles;
    }

    /**
     * 
     * @param estimatedDistanceMiles
     *     The estimated_distance_miles
     */
    public void setEstimatedDistanceMiles(Integer estimatedDistanceMiles) {
        this.estimatedDistanceMiles = estimatedDistanceMiles;
    }

    /**
     * 
     * @return
     *     The estimatedCostCentsMax
     */
    public Integer getEstimatedCostCentsMax() {
        return estimatedCostCentsMax;
    }

    /**
     * 
     * @param estimatedCostCentsMax
     *     The estimated_cost_cents_max
     */
    public void setEstimatedCostCentsMax(Integer estimatedCostCentsMax) {
        this.estimatedCostCentsMax = estimatedCostCentsMax;
    }

    /**
     * 
     * @return
     *     The primetimePercentage
     */
    public String getPrimetimePercentage() {
        return primetimePercentage;
    }

    /**
     * 
     * @param primetimePercentage
     *     The primetime_percentage
     */
    public void setPrimetimePercentage(String primetimePercentage) {
        this.primetimePercentage = primetimePercentage;
    }

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 
     * @return
     *     The estimatedCostCentsMin
     */
    public Integer getEstimatedCostCentsMin() {
        return estimatedCostCentsMin;
    }

    /**
     * 
     * @param estimatedCostCentsMin
     *     The estimated_cost_cents_min
     */
    public void setEstimatedCostCentsMin(Integer estimatedCostCentsMin) {
        this.estimatedCostCentsMin = estimatedCostCentsMin;
    }

    /**
     * 
     * @return
     *     The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     *     The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 
     * @return
     *     The primetimeConfirmationToken
     */
    public Object getPrimetimeConfirmationToken() {
        return primetimeConfirmationToken;
    }

    /**
     * 
     * @param primetimeConfirmationToken
     *     The primetime_confirmation_token
     */
    public void setPrimetimeConfirmationToken(Object primetimeConfirmationToken) {
        this.primetimeConfirmationToken = primetimeConfirmationToken;
    }

}
