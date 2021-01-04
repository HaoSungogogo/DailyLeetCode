Intuition:
First, it is a LCS problems that could be solved in O(n ^ 2)
Second, the element in the target array is distinct. We can think the index of these elements decribing the arrangement.
So these elements have the value (index)
Third, this problem is transformed into finding the LIS of the value(index) in arr


class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            if (!map.containsKey(a)) continue;
            int val = map.get(a);
            if (list.size() == 0 || list.get(list.size() - 1) < val) {
                list.add(val);
            } else {
                int idx = find(list, val);
                list.set(idx, val);
            }
        }
        return target.length - list.size();
    }

    private int find(List<Integer> list, int value) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (list.get(left) >= value) {
            return left;
        }
        return right;
    }
}
