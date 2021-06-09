package uz.bank.web.kredit.utils;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import uz.bank.web.kredit.dto.LoanOrderDto;
import uz.bank.web.kredit.model.LoanOrder;
import uz.bank.web.kredit.payload.ApiResponse;
import uz.bank.web.kredit.payload.ApiResponseObject;

import java.nio.charset.StandardCharsets;


@Component
public class Converter {


    /** For responses **/

    public ApiResponse api(String message, boolean success){
        return new ApiResponse(message,success);
    }

    public ApiResponse api(String message, boolean success,Object object){
        return new ApiResponseObject(message,success,object);
    }



    public ApiResponse apiError(){
        return api("Error",false);
    }

    public ApiResponse apiError(String message){
        return api(message,false);
    }

    public ApiResponse apiError(Object object){
        return api("Error",false,object);
    }

    public ApiResponse apiError(String message, Object object){
        return api(message,false,object);
    }


    public ApiResponse apiSuccess(){
        return api("Success",true);
    }

    public ApiResponse apiSuccess(String message){
        return api(message,true);
    }

    public ApiResponse apiSuccess(Object object){
        return api("Success",true,object);
    }

    public ApiResponse apiSuccess(String message, Object object){
        return api(message,true,object);
    }




    public HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

 // For data transfer objects
    public LoanOrderDto orderToDto(LoanOrder order) {
        return LoanOrderDto
                .builder()
                .amount(order.getLoanAmount())
                .fullName(order.getCustomer().getPassport().getName()+" "+order.getCustomer().getPassport().getSurname())
                .loan_code(order.getId())
                .full_amount(order.getFinalLoanAmount())
                .duration(order.getDuration())
                .passport_number(order.getCustomer().getPassport().getNumber())
                .passport_series(order.getCustomer().getPassport().getSeries())
                .build();
    }
}
