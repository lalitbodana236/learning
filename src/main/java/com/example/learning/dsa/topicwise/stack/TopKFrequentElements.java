package com.example.learning.dsa.topicwise.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Freq> minHeap =
                new PriorityQueue<>((a, b) -> a.freq - b.freq);
        
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int i = entry.getKey();
            int j = entry.getValue();
            minHeap.offer(new Freq(i, j));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        
        int[] result = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll().val;
        }
        
        return result;
    }
}

class Freq {
    Integer val;
    Integer freq;
    
    public Freq(int val, int freq) {
        this.val = val;
        this.freq = freq;
    }
    
}
