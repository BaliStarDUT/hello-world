package hibernate;
import java.util.Date;

public class NginxLog{
    private int id;
    private String remote_addr;
    private String remote_user;
    private Date time_local;
    private String request;
    private int status;
    private int  request_length;
    private String http_referer;
    private String http_user_agent;
    private String request_time;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getRemote_addr() {
        return remote_addr;
    }

    public void setRemote_addr(String remote_addr) {
        this.remote_addr = remote_addr;
    }

    public String getRemote_user() {
        return remote_user;
    }

    public void setRemote_user(String remote_user) {
        this.remote_user = remote_user;
    }

    public Date getTime_local() {
        return time_local;
    }

    public void setTime_local(Date time_local) {
        this.time_local = time_local;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRequest_length() {
        return request_length;
    }

    public void setRequest_length(int request_length) {
        this.request_length = request_length;
    }

    public String getHttp_referer() {
        return http_referer;
    }

    public void setHttp_referer(String http_referer) {
        this.http_referer = http_referer;
    }

    public String getHttp_user_agent() {
        return http_user_agent;
    }

    public void setHttp_user_agent(String http_user_agent) {
        this.http_user_agent = http_user_agent;
    }

    public String getRequest_time() {
        return request_time;
    }

    public void setRequest_time(String request_time) {
        this.request_time = request_time;
    }

}
