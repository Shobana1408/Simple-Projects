class RiskThresholdBinaryLookup {
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear: threshold=" + target + " -> found at index " + i + " (" + comparisons + " comps)");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear: threshold=" + target + " -> not found (" + comparisons + " comps)");
        }
    }
    public static int floorValue(int[] risks, int target, int[] comparisonHolder) {
        int low = 0;
        int high = risks.length - 1;
        int floor = -1;
        int comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (risks[mid] == target) {
                comparisonHolder[0] = comparisons;
                return risks[mid];
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        comparisonHolder[0] = comparisons;
        return floor;
    }
    public static int ceilingValue(int[] risks, int target) {
        int low = 0;
        int high = risks.length - 1;
        int ceiling = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (risks[mid] == target) {
                return risks[mid];
            } else if (risks[mid] < target) {
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }
        return ceiling;
    }
    public static int insertionPoint(int[] risks, int target) {
        int low = 0;
        int high = risks.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (risks[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        int target = 30;
        System.out.print("Sorted risks: [");
        for (int i = 0; i < risks.length; i++) {
            System.out.print(risks[i]);
            if (i < risks.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        linearSearch(risks, target);
        int[] holder = new int[1];
        int floor = floorValue(risks, target, holder);
        int ceiling = ceilingValue(risks, target);
        int insertion = insertionPoint(risks, target);
        System.out.println("Binary floor(" + target + "): " + floor + ", ceiling: " + ceiling + " (" + holder[0] + " comps)");
        System.out.println("Insertion point for " + target + ": " + insertion);
    }
}
