package carcrowdsystem.ccs.dtos.historico;


public class CheckoutSemanalResponse {
    private String data;
    private String totalCheckouts;

    public CheckoutSemanalResponse(String data, String totalCheckouts) {
        this.data = data;
        this.totalCheckouts = totalCheckouts;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTotalCheckouts() {
        return totalCheckouts;
    }

    public void setTotalCheckouts(String totalCheckouts) {
        this.totalCheckouts = totalCheckouts;
    }
}
