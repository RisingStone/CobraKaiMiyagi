
package cobrakai.com.miyagi.models.uber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("localized_display_name")
    @Expose
    private String localizedDisplayName;
    @SerializedName("high_estimate")
    @Expose
    private Object highEstimate;
    @SerializedName("minimum")
    @Expose
    private Object minimum;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("estimate")
    @Expose
    private String estimate;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("low_estimate")
    @Expose
    private Object lowEstimate;
    @SerializedName("surge_multiplier")
    @Expose
    private Integer surgeMultiplier;
    @SerializedName("currency_code")
    @Expose
    private Object currencyCode;

    /**
     * 
     * @return
     *     The localizedDisplayName
     */
    public String getLocalizedDisplayName() {
        return localizedDisplayName;
    }

    /**
     * 
     * @param localizedDisplayName
     *     The localized_display_name
     */
    public void setLocalizedDisplayName(String localizedDisplayName) {
        this.localizedDisplayName = localizedDisplayName;
    }

    /**
     * 
     * @return
     *     The highEstimate
     */
    public Object getHighEstimate() {
        return highEstimate;
    }

    /**
     * 
     * @param highEstimate
     *     The high_estimate
     */
    public void setHighEstimate(Object highEstimate) {
        this.highEstimate = highEstimate;
    }

    /**
     * 
     * @return
     *     The minimum
     */
    public Object getMinimum() {
        return minimum;
    }

    /**
     * 
     * @param minimum
     *     The minimum
     */
    public void setMinimum(Object minimum) {
        this.minimum = minimum;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The estimate
     */
    public String getEstimate() {
        return estimate;
    }

    /**
     * 
     * @param estimate
     *     The estimate
     */
    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
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
     *     The productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 
     * @param productId
     *     The product_id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 
     * @return
     *     The lowEstimate
     */
    public Object getLowEstimate() {
        return lowEstimate;
    }

    /**
     * 
     * @param lowEstimate
     *     The low_estimate
     */
    public void setLowEstimate(Object lowEstimate) {
        this.lowEstimate = lowEstimate;
    }

    /**
     * 
     * @return
     *     The surgeMultiplier
     */
    public Integer getSurgeMultiplier() {
        return surgeMultiplier;
    }

    /**
     * 
     * @param surgeMultiplier
     *     The surge_multiplier
     */
    public void setSurgeMultiplier(Integer surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    public Object getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currency_code
     */
    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Price{" +
                "localizedDisplayName='" + localizedDisplayName + '\'' +
                ", highEstimate=" + highEstimate +
                ", minimum=" + minimum +
                ", duration=" + duration +
                ", estimate='" + estimate + '\'' +
                ", distance=" + distance +
                ", displayName='" + displayName + '\'' +
                ", productId='" + productId + '\'' +
                ", lowEstimate=" + lowEstimate +
                ", surgeMultiplier=" + surgeMultiplier +
                ", currencyCode=" + currencyCode +
                '}';
    }
}
