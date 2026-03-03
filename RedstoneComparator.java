public class RedstoneComparator {


    /**
     * If either side input is greater than the rear input, the comparator output turns off. 
     * If neither side input is greater than the rear input, the comparator outputs the same signal strength as its rear input.
    */
    public static int compare(int rear, int left, int right) {
        int compareSideStrengths = (left <= rear && right <= rear) ? 1 : 0;
        return rear * compareSideStrengths;
    }

    // Subtracts the side input with higher signal strength from the signal strength of the rear input
    public static int subtract(int rear, int left, int right) {
        return Math.max(rear - Math.max(left, right), 0);
    }

    public static void main(String[] args) {
        
        System.out.println("COMPARE");
        System.out.println(compare(15, 16, 15));

        System.out.println("SUBTRACT");
        System.out.println(subtract(15, 5, 0));

    }
    
}
