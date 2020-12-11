package com.xh.common;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseObject implements Serializable {
    
    private HttpStatus status;
    
    private String msg;
    
    private Object object;
    
    private ResponseObject(Builder builder) {
        this.status = builder.status;
        this.msg = builder.msg;
        this.object = builder.result;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public Object getObject() {
        return object;
    }
    
    @Override
    public String toString() {
        return "ResponseObject{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", object=" + object +
                '}';
    }
    
    public static class Builder {
        private HttpStatus status;
        private String msg;
        private Object result;
        
        public Builder status(HttpStatus status){
            this.status = status;
            return this;
        }
    
        public Builder msg(String msg){
            this.msg = msg;
            return this;
        }
    
        public Builder result(Object result){
            this.result = result;
            return this;
        }
        
        public ResponseObject build(){
            return new ResponseObject(this);
        }
        
        public ResponseObject pureSuccess(){
            this.status = HttpStatus.OK;
            return new ResponseObject(this);
        }
        
        public ResponseObject successWithRes(Object result){
            this.status = HttpStatus.OK;
            this.result = result;
            return new ResponseObject(this);
        }
        
        public ResponseObject errorWithMsg(String msg){
            this.status = HttpStatus.OK;
            this.msg = msg;
            return new ResponseObject(this);
        }
        
        public ResponseObject successWithMsg(String msg){
            this.status = HttpStatus.OK;
            this.msg = msg;
            return new ResponseObject(this);
        }
        
        public ResponseObject UNAUTHORIZED(){
            this.status = HttpStatus.UNAUTHORIZED;
            this.msg = "";
            return new ResponseObject(this);
        }
        
    }
    
}
