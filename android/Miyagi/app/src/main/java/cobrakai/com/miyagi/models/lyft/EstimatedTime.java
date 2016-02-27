package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gareoke on 2/26/16.
 */
public class EstimatedTime {
    @SerializedName("eta_estimates") private ArrayList<EstimatedTimeObject> etaEstimates;

    public EstimatedTime(ArrayList<EstimatedTimeObject> etaEstimates) {
        this.etaEstimates = etaEstimates;
    }

    public ArrayList<EstimatedTimeObject> getEtaEstimates() {
        return etaEstimates;
    }
}
