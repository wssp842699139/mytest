package com.test.exception;

import org.springframework.http.ResponseEntity;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/17 19:58
 * @package com.test.exception
 */
public class RemotionServiceException extends Exception {
    private int status;
    private String message;

    public RemotionServiceException(int status, String message) {

        this.status = status;
        this.message = message;

    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return this.status;
    }

    public ResponseEntity toResponse(){
        return ResponseEntity.status(this.status).body(this.message);
    }
}
