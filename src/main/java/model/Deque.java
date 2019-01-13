package model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class Deque {
    private static final Log log = LogFactory.getLog(Deque.class);
    private Queue<Integer> headStack;
    private Queue<Integer> tailStack;
    private Queue<Integer> bufferStack;

    public Deque() {
        headStack = new LinkedList<>();
        tailStack = new LinkedList<>();
        bufferStack = new LinkedList<>();
    }

    public boolean offerFirst(Integer value) {
        rebalance();
        return headStack.offer(value);
    }

    public boolean offerLast(Integer value) {
        rebalance();
        return tailStack.offer(value);
    }

    public Integer peekFirst() {
        if (headStack.isEmpty()){
            rebalance();
        }
        return headStack.peek();
    }

    public Integer peekLast() {
        if (tailStack.isEmpty()) {
            rebalance();
        }
        return tailStack.peek();
    }

    public Integer pollFirst() {
        if (headStack.isEmpty()){
            rebalance();
        }
        return headStack.poll();
    }

    public Integer pollLast() {
        if (tailStack.isEmpty()){
            rebalance();
        }
        return tailStack.poll();
    }

    public void clear() {
        headStack.clear();
        tailStack.clear();
        bufferStack.clear();
    }

    public int size() {
        return headStack.size() + tailStack.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void rebalance() {
        if (size() == 0) {
            log.debug("Deque is empty, no need to rebalance.");
        } else if (headStack.isEmpty()) {
            rebalanceStacks(tailStack, headStack, bufferStack);
        } else if (tailStack.isEmpty()) {
            rebalanceStacks(headStack, tailStack, bufferStack);
        }
        // no need to do anything if neither stack is empty
    }

    private void rebalanceStacks(Queue<Integer> fromStack, Queue<Integer> toStack, Queue<Integer> bufferStack) {
        int originalSize = fromStack.size();
        while (fromStack.size() > (originalSize ) / 2) {
            bufferStack.add(fromStack.poll());
        }
        while (!fromStack.isEmpty()) {
            toStack.offer(fromStack.poll());
        }
        while (!bufferStack.isEmpty()) {
            toStack.offer(bufferStack.poll());
        }
    }
}
