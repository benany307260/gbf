package com.bentest.gbf.controller.response;
/** 
 * 类名：管理系统错误码<br> 
 * 类说明：<br> 
 *  
 */  
public enum ErrorCode {  

	/****************通用错误码******************/
	
    @Error(msg="鉴权不通过，请检查账号，密码，时间戳，固定串，以及MD5算法是否按照文档要求进行设置")  
    e1_100001,  
    
    
    
      
    @Error(msg="系统异常")  
    e1_109999;  
    
    
    
    /** 
     * 返回错误码 
     */  
    public String getCode(){  
        return this.name();  
    }  
      
    /** 
     * 返回错误信息 
     */  
    public String getMessage(){  
        Error error = null;  
        try {  
            error = this.getClass().getField(this.getCode()).getAnnotation(Error.class);  
        } catch (Exception e) {  
            return null;  
        }  
        return error.msg();  
    }  
  
}