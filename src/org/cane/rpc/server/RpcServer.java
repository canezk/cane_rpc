/**
 * @(#)RpcServer.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Base server class which is abstract.
 * @author zhoukang
 *
 */
public abstract class RpcServer {
    
    protected String serverAddress;

    /**
     * Used to store proxy_objects
     */
    protected Map<Class<?>, List<Object>> proxy_objects = new HashMap<Class<?>, List<Object>>();
    
    /**
     * Start a server
     */
    public abstract void start();
    
    /**
     * Stop a server
     */
    public abstract void stop();
    
    /**
     * Fill the hash map,key:interface service,value:list of proxy implementation
     * NOTE:can be overrided
     * @param proxys
     */
    public void setProxyObjects(Map<Class<?>, List<Object>> proxys) {
        for(Entry<Class<?>, List<Object>> proxy : proxys.entrySet()) {
            proxy_objects.put(proxy.getKey(), proxy.getValue());
        }
    }
}
