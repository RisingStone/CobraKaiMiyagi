
package cobrakai.com.miyagi.models.lyft;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CostEstimates {

    @SerializedName("cost_estimates")
    @Expose
    private List<CostEstimate> costEstimates = new ArrayList<CostEstimate>();

    /**
     * 
     * @return
     *     The costEstimates
     */
    public List<CostEstimate> getCostEstimates() {
        return costEstimates;
    }

    /**
     * 
     * @param costEstimates
     *     The cost_estimates
     */
    public void setCostEstimates(List<CostEstimate> costEstimates) {
        this.costEstimates = costEstimates;
    }

}
