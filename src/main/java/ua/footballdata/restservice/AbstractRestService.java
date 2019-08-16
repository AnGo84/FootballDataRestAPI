package ua.footballdata.restservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;


public abstract class AbstractRestService<T> implements RestService<T> {
    private String token;
    private HttpEntity<String> httpEntitis;

    /*public AbstractRestService() {
    }*/

    public AbstractRestService(String token) {
        this.token = token;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("X-Auth-Token", token);

        httpEntitis = new HttpEntity<String>("request", httpHeaders);

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HttpEntity<String> getHttpEntitis() {
        return httpEntitis;
    }

    public void setHttpEntitis(HttpEntity<String> httpEntitis) {
        this.httpEntitis = httpEntitis;
    }

}
