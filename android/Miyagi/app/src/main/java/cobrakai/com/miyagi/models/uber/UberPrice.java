
package cobrakai.com.miyagi.models.uber;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UberPrice {

    @SerializedName("prices")
    @Expose
    private List<Price> prices = new ArrayList<Price>();

    /**
     * 
     * @return
     *     The prices
     */
    public List<Price> getPrices() {
        return prices;
    }

    /**
     * 
     * @param prices
     *     The prices
     */
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "UberPrice{" +
                "prices=" + prices +
                '}';
    }
}
