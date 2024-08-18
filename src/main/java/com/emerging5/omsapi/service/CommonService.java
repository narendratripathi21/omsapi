package com.emerging5.omsapi.service;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
    public static boolean isValidString(String input){
        return input.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }
    public static boolean isValidCron(String input){
        return CronExpression.isValidExpression(input);
    }
    public static boolean isValidEmail(String input){
        return input.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }
    public static boolean sendMail(){
        
        return true;
    }

    public static String getMessage(String type, Class cs, String error){
        switch (type) {
            case "created":
                return cs.getName() + " created successfully. ";
            case "updated":
                return cs.getName() + " updated successfully. ";
            case "completed":
                return cs.getName() + " completed successfully. ";
            case "missing":
                return "transaction failed, as "+ cs.getName() + " not found!";
            case "invalid":
                return cs.getName() + ", transaction failed, due to invalid params";
            default:
                return cs.getName() + " completed successfully ";
        }
    }

    public static enum objectTxnStatus{
        created,
        updated,
        missing,
        invalid,
        completed,
    };
}
