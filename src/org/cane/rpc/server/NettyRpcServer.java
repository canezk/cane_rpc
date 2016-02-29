/**
 * @(#)NettyRpcServer.java, 2016年2月27日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cane.rpc.servicectrl.ServiceRegistry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
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
    
    private ServiceRegistry serviceRegistry;
    
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;

    public NettyRpcServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }
    
    public NettyRpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;
    }
    
    /**
     * setter for serviceregistry
     * @param serviceRegistry
     */
    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
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
                    channel.pipeline().addLast();
                }
            });
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            String[] hostAndPort = serverAddress.split(":");
            
            ChannelFuture future = serverBootstrap.bind(hostAndPort[0], Integer.parseInt(hostAndPort[1])).sync();
            
            if(serviceRegistry != null) {
                serviceRegistry.registerServer(serverAddress);
            }
            
            future.channel().closeFuture().sync();            
        } catch(Exception e) {
            LOG.error("Init rpc server error!", e);
        }
    }
    
    @Override
    public void stop() {
        
    }
}
