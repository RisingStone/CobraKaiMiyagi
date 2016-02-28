
package cobrakai.com.miyagi.models.miyagi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hub {

    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("__v")
    @Expose
    private Integer V;
    @SerializedName("area")
    @Expose
    private Area area;

    @SerializedName("hub")
    @Expose
    private HubPoint hubPoint;

    public HubPoint getHubPoint() {
        return hubPoint;
    }

    public void setHubPoint(HubPoint hubPoint) {
        this.hubPoint = hubPoint;
    }

    /**
     * 
     * @return
     *     The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The V
     */
    public Integer getV() {
        return V;
    }

    /**
     * 
     * @param V
     *     The __v
     */
    public void setV(Integer V) {
        this.V = V;
    }

    /**
     * 
     * @return
     *     The area
     */
    public Area getArea() {
        return area;
    }

    /**
     * 
     * @param area
     *     The area
     */
    public void setArea(Area area) {
        this.area = area;
    }

}
