/**
 * @(#)RpcServerFactory.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

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
    public RpcServer getRpcServer(String serverAddress, Class rpcServerClass) {
        try {
            RpcServer rpcServer = (RpcServer)rpcServerClass.newInstance();   
            return rpcServer;         
        } catch (IllegalAccessException e) {
            //TODO:
        } catch (InstantiationException e) {
            //TODO:
        }
        return null;
    }

}
