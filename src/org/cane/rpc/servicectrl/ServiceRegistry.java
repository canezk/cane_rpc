/**
 * @(#)ServiceRegistry.java, 2016年2月29日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.servicectrl;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * When a new server added, we create a znode in zookeeper
 * @author zhoukang
 *
 */
public class ServiceRegistry {
    private static final Log LOG = LogFactory.getLog(ServiceRegistry.class);
    
    private String zookeeperAddress;
    
    private CountDownLatch latch = new CountDownLatch(1);
    
    public ServiceRegistry(String zookeeperAddress) {
        this.zookeeperAddress = zookeeperAddress;
    }
    
    /**
     * used for spring auto-inject
     * @param zookeeperAddress
     */
    public void setZookeeperAddress(String zookeeperAddress) {
        this.zookeeperAddress = zookeeperAddress;
    }

    /**
     * register server in zookeeper
     * @param data
     */
    public void registerServer(String data) {
        registerWithPath(data, ServiceConstant.SERVER_ADDRESS);
    }
    
    /**
     * create a znode under the given path
     * @param data
     * @param path
     */
    private void registerWithPath(String data, String path) {
        if(data != null) {
            ZooKeeper zk = connectZookeeperServer();
            if(zk != null) {
                createZnode(zk, data, path);
            }
        }
    }
    
    /**
     * create connection to zookeeper server
     * @return
     */
    private ZooKeeper connectZookeeperServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(zookeeperAddress, ServiceConstant.SESSION_TIMEOUT, new Watcher() {
                
                @Override
                public void process(WatchedEvent event) {
                    if(event.getState() == KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch(Exception e) {
            LOG.error("Can not connect given zookeeper server!", e);
        }
        return zk;
    }
    
    /**
     * create znode
     * @param zk
     * @param data
     * @param path
     */
    public void createZnode(ZooKeeper zk, String data, String path) {
        try {
            byte[] bytes = data.getBytes();
            zk.create(path, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException | InterruptedException e) {
            LOG.error("Create znode error!", e);
        }
    }
}
