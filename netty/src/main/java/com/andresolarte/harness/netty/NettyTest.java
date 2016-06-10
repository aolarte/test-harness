package com.andresolarte.harness.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.util.CharsetUtil;


import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;

public class NettyTest {

    static class DateHandler extends SimpleChannelHandler {
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            String s = (String)e.getMessage();
            System.out.println("Got data: " + s);

            super.messageReceived(ctx, e);
        }
    }

    public static void main(String... args) {
        bootServer();
    }


    public static void bootServer() {
        // More terse code to setup the server
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the pipeline factory.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new StringDecoder(CharsetUtil.UTF_8),
                        new DateHandler()
                );
            };
        });

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress("0.0.0.0", 8888));
        System.out.println("Listening on 8888");
    }
}
