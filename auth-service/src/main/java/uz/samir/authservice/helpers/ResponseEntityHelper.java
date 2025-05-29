package uz.samir.authservice.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.samir.authservice.payload.ApiResponse;

@Component
@RequiredArgsConstructor
public class ResponseEntityHelper {

    public ResponseEntity<ApiResponse> buildResponse(ApiResponse apiResponse) {
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    public ResponseEntity<ApiResponse> buildResponse(ApiResponse apiResponse, int isSuccess, int isError) {
        return ResponseEntity
                .status(apiResponse.isSuccess() ? isSuccess : isError)
                .body(apiResponse);
    }
}
