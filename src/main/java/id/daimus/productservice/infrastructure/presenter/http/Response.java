package id.daimus.productservice.infrastructure.presenter.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    private Object data;
    private Object page;
    private Object errors;
    private String error;
    private String message;
    private String path;
    private int httpCode = 200;

    public Response(Object dataParam){
        this.data = dataParam;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public void setError(Exception e){
        this.error = e.getClass().getSimpleName();
        this.message = e.getMessage();
    }

    public ResponseEntity<Object> getResponse(){
        Map<String, Object> response = new HashMap<String, Object>();
        // Data Handler
        if (this.data != null){
            response.put("data", this.data);
        } else {
            if (this.httpCode == 200 && this.errors == null){
                this.httpCode = 404;
            }
        }
        // Meta Handler
        Map<String, Object> meta = new HashMap<String, Object>();
        if (this.errors != null){
            meta.put("errors", this.errors);
            if (this.httpCode < 400){
                this.httpCode = 500;
            }
        }
        meta.put("code", httpCode);
        meta.put("timestamp", System.currentTimeMillis());
        if (this.error != null){
            meta.put("error", this.error);
            if (this.httpCode < 400){
                this.httpCode = 500;
            }
        }
        if (this.message != null){
            meta.put("message", this.message);
        }
        if (this.path != null){
            meta.put("path", this.path);
        }
        response.put("meta", meta);

        return new ResponseEntity<Object>(response, HttpStatusCode.valueOf(httpCode));
    }
}
