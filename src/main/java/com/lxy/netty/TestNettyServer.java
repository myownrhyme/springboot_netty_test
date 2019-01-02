package com.lxy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class TestNettyServer extends ChannelInboundHandlerAdapter{

    public void channelRead(ChannelHandlerContext ctx ,Object msg){
        ByteBuf bf =((ByteBuf)msg);
        try{
           while (bf.isReadable()){
               System.out.println((char)bf.readByte());
               System.out.flush();
           }
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
   }
}
