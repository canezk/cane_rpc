/**
 * @(#)RpcServer.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

/**
 * Base server class which is abstract.
 * @author zhoukang
 *
 */
public abstract class RpcServer {
    
    public abstract void start();
    
    public abstract void stop();
    
}
