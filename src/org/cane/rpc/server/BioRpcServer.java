/**
 * @(#)BioRpcServer.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author zhoukang
 *
 */
public class BioRpcServer extends RpcServer{
    private int port;
    private String ip;

    @Override
    public void start() {
        ServerSocket serverSocket = null; 
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.bind(new InetSocketAddress(ip , port));
            while(true) {
                Socket socket = serverSocket.accept();
            }
        } catch(IOException e) {
            //TODO
        }
    }
    
    @Override
    public void stop() {
        
    }
}
