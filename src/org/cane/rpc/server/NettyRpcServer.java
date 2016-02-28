/**
 * @(#)NettyRpcServer.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * Server implemented by netty.
 * @author zhoukang
 *
 */
public class NettyRpcServer extends RpcServer{
    private static final Log LOG = LogFactory.getLog(NettyRpcServer.class);
    
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    public NettyRpcServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }
    
    @Override
    public void start() {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(null);
                    //TODO
                }
            });
            String[] hostAndPort = serverAddress.split(":");
            serverBootstrap.bind(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
        } catch(Exception e) {
            LOG.error("Init rpc server error!", e);
        }
    }
    
    @Override
    public void stop() {
        
    }
}
