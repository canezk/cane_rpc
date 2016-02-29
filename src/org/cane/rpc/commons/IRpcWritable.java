/**
 * @(#)IRpcWritable.java, 2016年2月29日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.commons;

/**
 * POJO class define the object transfer between client and server
 * @author zhoukang
 *
 */
public class IRpcWritable {
    private String requestId;
    
    private String className;
    
    private String methodName;
    
    private Class<?>[] parameterTypes;
    
    private Object[] params;
    
    private Throwable error;
    
    private Object result;
    
    //*********getter and setter****************//
    public void setReuqestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
    
    public void setParams(Object[] params) {
        this.params = params;
    }
    
    public Object[] getParams() {
        return params;
    }
    
    public void setError(Throwable error) {
        this.error = error;
    }
    
    public Throwable getError() {
        return error;
    }
    
    public void setResult(Object result) {
        this.result = result;
    }
    
    public Object getResult() {
        return result;
    }

}
