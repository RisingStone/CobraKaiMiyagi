package cobrakai.com.miyagi.models.miyagi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gareoke on 2/28/16.
 */
public class OK {
    @SerializedName("ok") protected String ok;

    public OK(String ok) {
        this.ok = ok;
    }

    public String getOk() {
        return ok;
    }
}
