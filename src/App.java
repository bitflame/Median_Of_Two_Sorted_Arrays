public class App {
    public static double findMedianSortedArrays(int[] arrayOne, int[] arrayTwo) {
        if (arrayOne == null || arrayOne.length == 0) {
            if (arrayTwo.length == 0 || arrayTwo == null)
                return 0;
            else {
                int n2 = arrayTwo.length;
                return (n2 % 2 == 0) ? (arrayTwo[n2 / 2 - 1] + arrayTwo[n2 / 2] * 1.0) / 2 : arrayTwo[n2 / 2] * 1.0;
            }
        }
        if (arrayTwo == null || arrayTwo.length == 0) {
            int n1 = arrayOne.length;
            return (n1 % 2 == 0) ? (arrayOne[n1 / 2 - 1] + arrayOne[n1 / 2] * 1.0) / 2 : arrayOne[n1 / 2] * 1.0;
        }
        if (arrayOne.length > arrayTwo.length) {
            int[] temp = arrayOne;
            arrayOne = arrayTwo;
            arrayTwo = temp;
        }
        int lo = 0, n1 = arrayOne.length, n2 = arrayTwo.length;
        int hi = n1, cut1, cut2, l1, l2, r1, r2;
        double result = 0;
        while (lo <= hi) {
            cut1 = lo + (hi - lo) / 2;
            cut2 = (n1 + n2) / 2 - cut1;
            // I am a bit hesitant about the line below since n1 is not neccessarily the
            // length of arrayOne
            l1 = (cut1 == 0) ? Integer.MIN_VALUE : arrayOne[cut1 - 1];
            l2 = (cut2 == 0) ? Integer.MIN_VALUE : arrayTwo[cut2 - 1];
            r1 = (cut1 == n1) ? Integer.MAX_VALUE : arrayOne[cut1];
            r2 = (cut2 == n2) ? Integer.MAX_VALUE : arrayTwo[cut2];
            if (l1 > r2) {
                hi = cut1 - 1;
            } else if (l2 > r1) {
                lo = cut1 + 1;
            } else {
                // multiplied by 1.0 to get the significant digits because I don't seem to be
                // able to cast below
                result = ((n1 + n2) % 2 == 0) ? (Math.max(l1, l2) + Math.min(r1, r2) * 1.0) / 2 : Math.min(r1, r2);
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("A program to find the median of two sorted arrays");
        int[] nums1 = { 1, 2, 3, 4, };
        int[] nums2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        System.out.printf("Test 1 - Expecting to get 3.5, actually getting: %f%n",
                findMedianSortedArrays(nums1, nums2));
        nums1 = new int[] { 1 };
        nums2 = null;
        System.out.println("Test 2 - Expecting to get 1.0, actually getting: " + findMedianSortedArrays(nums1, nums2));
        nums1 = null;
        nums2 = new int[] { 1, 1 };
        System.out.println("Test 3 - Expecting to get 1.0, actually getting: " + findMedianSortedArrays(nums1, nums2));
        nums1 = new int[] { 1 };
        nums2 = new int[] {};
        System.out.println("Test 4 - Expecting to get 1.0, actually getting: " + findMedianSortedArrays(nums1, nums2));
    }
}
