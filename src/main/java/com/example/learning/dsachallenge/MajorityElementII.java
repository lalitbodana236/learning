package com.example.learning.dsachallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        List<Integer> list = new ArrayList();
        
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (m.getValue() > nums.length / 3) {
                list.add(m.getKey());
            }
        }
        
        return list;
    }
}
