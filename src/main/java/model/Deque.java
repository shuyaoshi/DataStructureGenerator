package model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class Deque {
    private static final Log log = LogFactory.getLog(Deque.class);
    private LinkedList<Integer> headStack;
    private LinkedList<Integer> tailStack;
    private LinkedList<Integer> bufferStack;

    public Deque() {
        headStack = new LinkedList<>();
        tailStack = new LinkedList<>();
        bufferStack = new LinkedList<>();
    }

    public boolean offerFirst(Integer value) {
        return headStack.offerLast(value);
    }

    public boolean offerLast(Integer value) {
        return tailStack.offerLast(value);
    }

    public Integer peekFirst() {
        if (headStack.isEmpty()){
            rebalance();
        }
        return headStack.peekLast();
    }

    public Integer peekLast() {
        if (tailStack.isEmpty()) {
            rebalance();
        }
        return tailStack.peekLast();
    }

    public Integer pollFirst() {
        if (headStack.isEmpty()){
            rebalance();
        }
        return headStack.pollLast();
    }

    public Integer pollLast() {
        if (tailStack.isEmpty()){
            rebalance();
        }
        return tailStack.pollLast();
    }

    public boolean offerFirst(Collection<Integer> collections) {
        boolean isSuccess = true;
        for (Integer value : collections) {
            isSuccess = isSuccess && offerFirst(value);
        }
        return isSuccess;
    }

    public boolean offerLast(Collection<Integer> collections) {
        boolean isSuccess = true;
        for (Integer value : collections) {
            isSuccess = isSuccess && offerLast(value);
        }
        return isSuccess;
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

    private void rebalanceStacks(LinkedList<Integer> fromStack, LinkedList<Integer> toStack, LinkedList<Integer> bufferStack) {
        int originalSize = fromStack.size();
        while (fromStack.size() > (originalSize + 1) / 2) {
            bufferStack.offerLast(fromStack.pollLast());
        }
        while (!fromStack.isEmpty()) {
            toStack.offerLast(fromStack.pollLast());
        }
        while (!bufferStack.isEmpty()) {
            fromStack.offerLast(bufferStack.pollLast());
        }
    }
}
