package com.jessonzh.learning.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
    }
}
class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DoubleList {
        Node head;
        Node tail;
        public DoubleList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void remove(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        public void addFirst(Node node) {
            Node curr = head.next;
            node.next = curr;
            head.next = node;
            curr.prev = node;
            node.prev = head;
        }

        public Node removeTail() {
            Node last = tail.prev;
            last.prev.next = tail;
            tail.prev = last.prev;
            return last;
        }
    }

    private int capacity;

    Map<Integer, Node> map;

    DoubleList list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
        this.list = new DoubleList();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        list.remove(node);
        list.addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        Node curNode = map.get(key);
        if (curNode != null) {
            map.put(key, node);
            list.remove(curNode);
            list.addFirst(node);
        } else {
            if (map.entrySet().size() < capacity) {
                map.put(key, node);
                list.addFirst(node);
            } else {
                map.remove(list.removeTail().key);
                map.put(key, node);
                list.addFirst(node);
            }
        }
    }
}