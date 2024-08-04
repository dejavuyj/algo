package leetcode.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Code284_peeking_iterator {

    static class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer nextElement;

        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            this.nextElement = iterator.next();
        }

        public Integer peek() {
            return nextElement;
        }

        @Override
        public Integer next() {
            Integer ret = nextElement;
            nextElement = iterator.hasNext() ? iterator.next() : null;
            return ret;
        }

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Code284_peeking_iterator.PeekingIterator c = new Code284_peeking_iterator.PeekingIterator(list.iterator());
        System.out.println(c.next());
        System.out.println(c.peek());
        System.out.println(c.next());
        System.out.println(c.next());
        System.out.println(c.hasNext());
    }
}
