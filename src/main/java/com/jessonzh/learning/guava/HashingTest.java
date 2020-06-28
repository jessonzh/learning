package com.jessonzh.learning.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashingTest {
    public static void main(String[] args) {
        HashFunction hf = Hashing.sipHash24();
        // byte[] bytes = hf.hashString("test", Charsets.UTF_8).toString();
        System.out.println(hf.hashString("asdfasdfasdfasdfrqwerqwerqwerqwerqwrqwer", Charsets.UTF_8).toString());
    }
}