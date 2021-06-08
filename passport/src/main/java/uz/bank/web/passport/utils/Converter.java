package uz.bank.web.passport.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.bank.web.passport.payload.ApiResponse;
import uz.bank.web.passport.payload.ApiResponseObject;


import java.util.stream.Collectors;

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










}
