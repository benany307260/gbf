package com.bentest.gbf.Exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bentest.gbf.controller.response.ErrorCode;
import com.bentest.gbf.controller.response.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value=Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
		logger.error("捕获异常", e);
		if(e instanceof BusinessException) {
			BusinessException bex = (BusinessException)e;
			return Result.failure(bex.getErrorCode());
		}else if(e instanceof BindException) {
			BindException bindEx = (BindException)e;
			List<ObjectError> errorList = bindEx.getAllErrors();
			ObjectError error = errorList.get(0);
			String msg = error.getDefaultMessage();
			return Result.failure(msg);
		}else {
			return Result.failure(ErrorCode.e1_109999);
		}
	}
}
