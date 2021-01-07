flipped[] represents flip starting at i.
flip is the number of flip in the size K sliding window.
Greedy:
if we come across 0, flip = 0, we will add one flip
if we come across 1, flip = 1, we will add one flip

if i >= K, remove i - K flip => flip ^= flipped[i - K]


class Solution {
    public int minKBitFlips(int[] A, int K) {
        int size = A.length;
        int[] flipped = new int[size];
        int res = 0;
        int flip = 0;
        for (int i = 0; i < size; i++) {
            if (i >= K) flip ^= flipped[i - K];
            if ((A[i] ^ flip) == 0) {
                if (i + K > size) return -1;
                flip ^= 1;
                res++;
                flipped[i] = 1;
            }
        }
        return res;
    }
}
