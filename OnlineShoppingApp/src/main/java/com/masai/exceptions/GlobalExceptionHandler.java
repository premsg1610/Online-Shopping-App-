package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	    //-------------------------------------------------------------------------//
		//									ADMIN EXCEPTIONS
		//-------------------------------------------------------------------------//
		@ExceptionHandler(AdminException.class)
		public ResponseEntity<MyErrorDetails> sellerHandler(AdminException e, WebRequest wr) {
			
			MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(),wr.getDescription(false));
			
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}
		
	
	
		
		
		//-------------------------------------------------------------------------//
		//									CUSTOMER EXCEPTIONS
		//-------------------------------------------------------------------------//	
		
		@ExceptionHandler(CustomerException.class)
		public ResponseEntity<MyErrorDetails> sellerHandler(CustomerException e, WebRequest wr) {
			
            MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(),wr.getDescription(false));
			
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}
		
	
	
		
		
	
	
	
        //	if any logical error happens than this exception will be thrown.
	
		@ExceptionHandler(Exception.class)
		public ResponseEntity<MyErrorDetails> logicalException(Exception e, WebRequest wr){
		
			System.out.println("Inside the Exception Handler...");
			
			MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
			
			return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
				
	    }
	
		
		
		
		
	
	   //-------------------------------------------------------------------------//
	   //								VALIDATION EXCEPTIONS
	   //-------------------------------------------------------------------------//
	
	
       //	if user passes wrong api than this exception will be thrown automatically by spring boot
	
	   @ExceptionHandler(NoHandlerFoundException.class)
       public ResponseEntity<MyErrorDetails> wrongApiException(NoHandlerFoundException e, WebRequest wr){
		
		  System.out.println("Inside the NoHandlerFoundException Handler...");
		
		  MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		
		  return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		
	   }
	
	
	
      //	if user don't pass right argument than this exception will be thrown....
	
	   @ExceptionHandler(MethodArgumentNotValidException.class)
	   public ResponseEntity<MyErrorDetails> myIllegalHandler4(MethodArgumentNotValidException ie,WebRequest req) {
		
			System.out.println("inside MethodArgumentNotValidException Handler...");
			
			MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), req.getDescription(false));
			
			return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
			
	
	  }
	
	
}
