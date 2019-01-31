package com.lxy.webcontainer.App.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;

import javax.servlet.ServletContext;
import java.net.InetSocketAddress;


public class NettyServletContainer implements WebServer {

    private final InetSocketAddress address;
    private final ServletContext context;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private DefaultEventExecutorGroup dispatherExecutor;

    public NettyServletContainer(InetSocketAddress address, ServletContext context){
        this.address = address;
        this.context = context;
    }
    @Override
    public void start() throws WebServerException {
        ServerBootstrap sb = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(1);
        workGroup = new NioEventLoopGroup();
        dispatherExecutor = new DefaultEventExecutorGroup(50);
        sb.channel(NioServerSocketChannel.class)
                .group(bossGroup, workGroup);
        sb.option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_BACKLOG, 100);
        sb.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast("codec", new HttpServerCodec(4096, 8192, 8192, false)); //HTTP编码解码Handler
                p.addLast("servletInput", new NettyServletHandler(context)); //处理请求，读入数据，生成Request和Response对象
                p.addLast(dispatherExecutor, "filterChain", new RequestDispatcherHandler(context)); //获取请求分发器，让对应的Servlet处理请求
            }
        });
        ChannelFuture future = sb.bind(address).awaitUninterruptibly();
        Throwable cause = future.cause();
        if (null != cause) {
            throw new WebServerException("Could not start Netty server", cause);
        }
    }

    @Override
    public void stop() throws WebServerException {
        try {
            if (null != bossGroup) {
                bossGroup.shutdownGracefully().await();
            }
            if (null != workGroup) {
                workGroup.shutdownGracefully().await();
            }
            if (null != dispatherExecutor) {
                dispatherExecutor.shutdownGracefully().await();
            }
        } catch (InterruptedException e) {
            throw new WebServerException("Container stop interrupted", e);
        }
    }

    @Override
    public int getPort() {
        return address.getPort();
    }
}
