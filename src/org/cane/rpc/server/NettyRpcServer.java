/**
 * @(#)NettyRpcServer.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Server implemented by netty.
 * @author zhoukang
 *
 */
public class NettyRpcServer extends RpcServer{

    private Map<Class<?>, List<Object>> proxy_objects = new HashMap<Class<?>, List<Object>>();
    
    @Override
    public void start() {
        
    }
    
    @Override
    public void stop() {
        
    }
}
