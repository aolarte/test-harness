package com.andresolarte.harness.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.net.InetSocketAddress;
import java.util.List;

public class SimpleNettyTest {

    static class DataHandler extends ChannelInboundHandlerAdapter {
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            System.out.println("Got data: " + msg);

            super.channelRead(ctx, msg);
        }
    }

    static class UpperCaseDecoder extends MessageToMessageDecoder<String> {

        @Override
        protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
            out.add(msg.toUpperCase());
        }

    }

    public static void main(String... args) throws Exception {
        bootServer();
    }


    public static void bootServer() throws Exception {
        // More terse code to setup the server
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // Thread pool to execute slow handlers
        final EventExecutorGroup group = new DefaultEventExecutorGroup(15);


        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new UpperCaseDecoder());
                        pipeline.addLast(group, "dataHandler", new DataHandler());

                    }
                })
        ;


        // Bind and start to accept incoming connections.
        ChannelFuture f = bootstrap.bind(new InetSocketAddress("0.0.0.0", 8888)).sync();
        System.out.println("Listening on 8888");

        // Wait until the server socket is closed.
        f.channel().closeFuture().sync();
    }
}
