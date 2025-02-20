//Runtime: 14 ms, faster than 62.39% of Java online submissions for Top K Frequent Elements.
//Memory Usage: 42.9 MB, less than 6.89% of Java online submissions for Top K Frequent Elements.
class Node implements Comparable<Node>{
    int num;
    int count;
    public Node(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        return count - o.count;
    }
}


class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>(k);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num: nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num)+ 1);
            }
            else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Node> smallQ = new PriorityQueue<Node>(k);
        int count = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            count = entry.getValue();
            if(smallQ.size()<k) {
                smallQ.add(new Node(entry.getKey(), count));
                continue;
            }

            if(count > smallQ.peek().count) {
                smallQ.remove();
                smallQ.add(new Node(entry.getKey(), count));
            }
        }

        while(smallQ.size()>0) {
            res.add(smallQ.peek().num);
            smallQ.remove();
        }


        return res;
    }
}


class Node implements Comparable<Node>{
    int num;
    int count;
    public Node(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        return count - o.count;
    }
}
//Runtime: 11 ms, faster than 90.33% of Java online submissions for Top K Frequent Elements.
//Memory Usage: 40.5 MB, less than 68.10% of Java online submissions for Top K Frequent Elements.

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>(k);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Node> smallQ = new PriorityQueue<Node>(k);
        int count = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            count = entry.getValue();
            if(smallQ.size()<k) {
                smallQ.add(new Node(entry.getKey(), count));
                continue;
            }

            if(count > smallQ.peek().count) {
                smallQ.remove();
                smallQ.add(new Node(entry.getKey(), count));
            }
        }

        while(smallQ.size()>0) {
            res.add(smallQ.peek().num);
            smallQ.remove();
        }


        return res;
    }
}

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        if (nums.length == 0 || k == 0) return result;

        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int num: nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];

        for (int num: countMap.keySet()) {
            int freq = countMap.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        for (int freq = buckets.length-1; freq >=0; freq--) {
            if (buckets[freq] == null) continue;

            for (int element: buckets[freq]) {
                if (k-- > 0) {
                    result.add(element);
                }
            }
        }

        return result;
    }
}