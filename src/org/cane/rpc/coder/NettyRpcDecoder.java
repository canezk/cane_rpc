/**
 * @(#)NettyRpcDecoder.java, 2016年3月8日. 
 * 
 * Copyright 2016 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.cane.rpc.coder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 *
 * @author zhoukang
 *
 */
public class NettyRpcDecoder extends ByteToMessageDecoder{

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int dataLength = in.readInt();
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        //need to serizale here
        //TODO
        return;
    }
}
