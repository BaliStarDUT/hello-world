package hibernate;

import java.util.Date;

public class ShadowSocksLog{
    private int id;
    private Date datetime;
    private String type;
    private String request;
    private String fromAd;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getFromAd() {
        return fromAd;
    }

    public void setFromAd(String from) {
        this.fromAd = from;
    }

}
