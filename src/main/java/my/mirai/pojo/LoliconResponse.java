package my.mirai.pojo;

import java.util.Arrays;

public class LoliconResponse {

    String error;
    LoliconData[] data;

    public LoliconResponse() {
    }

    @Override
    public String toString() {
        return "LoliconResponse{" +
                "error='" + error + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LoliconData[] getData() {
        return data;
    }

    public void setData(LoliconData[] data) {
        this.data = data;
    }
}
