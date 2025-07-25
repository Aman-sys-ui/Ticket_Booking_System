package org.example.myException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

public class MyException extends Exception  {
    public MyException(String m){
        super(m);
    }public MyException(Throwable cause){
        super(cause);
    }public MyException(String m,Throwable cause){
        super(m,cause);
    }


}
