package com.andresolarte.harness.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DiscardStreamClientTest {

    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

        private ByteBuf content;
        private ChannelHandlerContext ctx;

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            this.ctx = ctx;

            // Initialize the message.
            content = ctx.alloc().directBuffer(SIZE).writeZero(SIZE);

            // Send the initial messages.
            generateTraffic();
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            content.release();
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            // Server is supposed to send nothing, but if it sends something, discard it.
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }

        long counter;

        private void generateTraffic() {
            // Flush the outbound buffer to the socket.
            // Once flushed, generate the same amount of traffic again.
            ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
        }

        private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    generateTraffic();
                } else {
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }
        };
    }


    public static void main(String... args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();

                            p.addLast(new DiscardClientHandler());
                        }
                    });

            // Make the connection attempt.
            ChannelFuture f = b.connect("localhost", 8888).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
