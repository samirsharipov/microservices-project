package uz.samir.authservice.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private String message;
    private boolean success;
    private Object object;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}

