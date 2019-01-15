package revature.models;

public class Reimbursement {

    private int reimbursementId;
    private int totalAmt;
    private int status;
    private String eusername;
    private String musername;
    private String receipt;
    private String requestSummary;


    public Reimbursement(String eusername, String requestSummary, int totalAmt, String receipt) {
        this.eusername = eusername;
        this.requestSummary = requestSummary; //To hold the description value
        this.totalAmt = totalAmt;
        this.receipt = receipt;
    }


    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public int getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(int totalAmt) {
        this.totalAmt = totalAmt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEusername() {
        return eusername;
    }

    public void setEusername(String eusername) {
        this.eusername = eusername;
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getRequestSummary() {
        return requestSummary;
    }

    public void setRequestSummary(String requestSummary) {
        this.requestSummary = requestSummary;
    }

}
