package com.andresolarte.harness.lang.queue;

import java.math.BigInteger;
import java.util.OptionalInt;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class BlockingQueueTest {
    public static void main(String... args) {
        long time1 = System.currentTimeMillis();
        BlockingQueue<BigInteger> startQueue = createQueue();
        BlockingQueue<BigInteger> queue1 = createQueue();
        BlockingQueue<BigInteger> queue2 = createQueue();
        BlockingQueue<BigInteger> queue3 = createQueue();
        BlockingQueue<BigInteger> endQueue = createQueue();

        startQueue.add(BigInteger.ZERO);

        AbstractRunner thread1 = new Runner1SimpleLoop(startQueue, queue1);
        AbstractRunner thread2 = new Runner2Recursive(queue1, queue2);
        AbstractRunner thread3 = new Runner3BitwiseAddition(queue2, queue3);
        AbstractRunner thread4 = new Runner4FlippingBits(queue3, endQueue);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            BigInteger integer = endQueue.take();
            long time2 = System.currentTimeMillis();
            System.out.println("Result: " + integer + ". Time " + (time2 - time1) + " millis");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static BlockingQueue<BigInteger> createQueue() {
        return new LinkedBlockingQueue<>();
    }

    public static abstract class AbstractRunner extends Thread {
        private final BlockingQueue<BigInteger> inQueue;
        private final BlockingQueue<BigInteger> outQueue;

        public AbstractRunner(BlockingQueue<BigInteger> inQueue, BlockingQueue<BigInteger> outQueue) {
            this.inQueue = inQueue;
            this.outQueue = outQueue;
        }

        @Override
        public void run() {
            try {
                BigInteger integer = inQueue.take();
                long time1 = System.currentTimeMillis();
                integer = increment(integer);
                long time2 = System.currentTimeMillis();
                System.out.println("Time " + (time2 - time1) + " millis");
                outQueue.add(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        abstract BigInteger increment(BigInteger integer);
    }

    public static class Runner1SimpleLoop extends AbstractRunner {
        public Runner1SimpleLoop(BlockingQueue<BigInteger> inQueue, BlockingQueue<BigInteger> outQueue) {
            super(inQueue, outQueue);
        }

        BigInteger increment(BigInteger integer) {
            BigInteger ret = integer;
            for (int i = 0; i < 400; i++) {
                ret = ret.add(BigInteger.ONE);
            }
            return ret;
        }
    }


    public static class Runner2Recursive extends AbstractRunner {
        public Runner2Recursive(BlockingQueue<BigInteger> inQueue, BlockingQueue<BigInteger> outQueue) {
            super(inQueue, outQueue);
        }

        BigInteger increment(BigInteger integer) {
            return step(integer, 400); //Go into the recursive call
        }

        BigInteger step(BigInteger integer, int steps) {
            return steps == 0 ? integer : step(integer.add(BigInteger.ONE), steps - 1);
        }
    }


    public static class Runner3BitwiseAddition extends AbstractRunner {
        public Runner3BitwiseAddition(BlockingQueue<BigInteger> inQueue, BlockingQueue<BigInteger> outQueue) {
            super(inQueue, outQueue);
        }

        BigInteger increment(BigInteger integer) {
            BigInteger ret = integer;
            for (int i = 0; i < 400; i++) {
                ret = bitwiseAdditionWithCarry(ret, BigInteger.ONE);
            }
            return ret;
        }

        private BigInteger bitwiseAdditionWithCarry(BigInteger x, BigInteger y) {
            while (y.compareTo(BigInteger.ZERO) != 0) {
                BigInteger carry = x.and(y);
                x = x.xor(y);
                y = carry.shiftLeft(1);
            }
            return x;
        }
    }

    public static class Runner4FlippingBits extends AbstractRunner {
        public Runner4FlippingBits(BlockingQueue<BigInteger> inQueue, BlockingQueue<BigInteger> outQueue) {
            super(inQueue, outQueue);
        }

        BigInteger increment(BigInteger integer) {
            BigInteger ret = integer;
            for (int i = 0; i < 400; i++) {
                ret = flipBits(ret);
            }
            return integer.add(BigInteger.valueOf(400));
        }

        private BigInteger flipBits(BigInteger ret) {
            boolean done = false;
            int index = 0;
            while (!done) {
                //We loop until we find a ZERO
                if (!ret.testBit(index)) {
                    done = true;
                }
                ret = ret.flipBit(index);
                index++;
            }
            return ret;
        }
    }

    public static class RunnerLambda extends AbstractRunner {
        public RunnerLambda(BlockingQueue<BigInteger> inQueue, BlockingQueue<BigInteger> outQueue) {
            super(inQueue, outQueue);
        }

        BigInteger increment(BigInteger integer) {
            OptionalInt optionalInt = IntStream.rangeClosed(1, 400).map(i -> integer.intValue() + i).max();
            return BigInteger.valueOf(optionalInt.getAsInt());
        }
    }
}
