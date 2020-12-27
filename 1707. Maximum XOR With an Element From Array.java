/***
1. For XOR problems, we want to work on the bit level
2. To avoid comparing one by one, we can compress the nums array value by storing on the TrieTree
3. Sort each array to restrict values stored in the tree are less than queries[i][1]
***/

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        TrieNode root = new TrieNode();
        int[][] sortedQueries = new int[len][3];
        for (int i = 0; i < len; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], i};
        }
        Arrays.sort(nums);
        Arrays.sort(sortedQueries, (a, b) -> a[1] - b[1]);
        int[] res = new int[len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            while (index < nums.length && nums[index] <= sortedQueries[i][1]) {
                addNode(root, nums[index]);
                index++;
            }
            if (nums[0] > sortedQueries[i][1]) {
                res[sortedQueries[i][2]] = -1;
            } else {
                res[sortedQueries[i][2]] = getMaxXOR(root, sortedQueries[i][0]);
            }
        }
        return res;
    }

    static class TrieNode {
        TrieNode[] children;
        TrieNode() {
            this.children = new TrieNode[2];
        }
    }

    private void addNode(TrieNode root, int val) {
        TrieNode curNode = root;
        for (int i = 31; i >= 0; i--) {
            int curVal = (val >> i) & 1;
            if (curNode.children[curVal] == null) {
                curNode.children[curVal] = new TrieNode();
            }
            curNode = curNode.children[curVal];
        }
    }

    private int getMaxXOR(TrieNode root, int val) {
        int res = 0;
        TrieNode curNode = root;
        for (int i = 31; i >= 0; i--) {
            if (curNode == null) break;
            int curVal = (val >> i) & 1;
            if (curNode.children[curVal ^ 1] != null) {
                res += (1 << i);
                curNode = curNode.children[curVal ^ 1];
            } else {
                curNode = curNode.children[curVal];
            }
        }
        return res;
    }
}
