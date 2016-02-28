/**
 * @(#)RpcServerFactory.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Factory class for rpc server.
 * @author zhoukang
 *
 */
public class RpcServerFactory {
    
    /**
     * get rpc server which depended on class info
     * @param serverAddress
     * @param rpcServerClass
     * @return
     */
    public RpcServer getRpcServer(String serverAddress, Class rpcServerClass) 
            throws NoSuchMethodException, InvocationTargetException{
        try {
            RpcServer rpcServer = (RpcServer)rpcServerClass.newInstance(); 
            Class[] args = {String.class};
            Constructor constructor = rpcServerClass.getConstructor(args);
            Object[] parms = {serverAddress};
            return (RpcServer)constructor.newInstance(parms);        
        } catch (IllegalAccessException e) {
            //TODO:
        } catch (InstantiationException e) {
            //TODO:
        }
        return null;
    }

}
