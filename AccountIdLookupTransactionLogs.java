class TransactionLog {
    String accountId;
    public TransactionLog(String accountId) {
        this.accountId = accountId;
    }
}
class AccountIdLookupTransactionLogs {
    public static void linearSearchFirst(TransactionLog[] logs, String target) {
        int comparisons = 0;
        int index = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].accountId.equals(target)) {
                index = i;
                break;
            }
        }
        System.out.println("Linear first " + target + ": index " + index + " (" + comparisons + " comparisons)");
    }
    public static void linearSearchLast(TransactionLog[] logs, String target) {
        int comparisons = 0;
        int index = -1;
        for (int i = logs.length - 1; i >= 0; i--) {
            comparisons++;
            if (logs[i].accountId.equals(target)) {
                index = i;
                break;
            }
        }
        System.out.println("Linear last " + target + ": index " + index + " (" + comparisons + " comparisons)");
    }
    public static int binarySearch(TransactionLog[] logs, String target, int[] comparisonHolder) {
        int low = 0;
        int high = logs.length - 1;
        int comparisons = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].accountId.compareTo(target);
            if (cmp == 0) {
                comparisonHolder[0] = comparisons;
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        comparisonHolder[0] = comparisons;
        return -1;
    }
    public static int countOccurrences(TransactionLog[] logs, String target) {
        int count = 0;
        for (TransactionLog log : logs) {
            if (log.accountId.equals(target)) {
                count++;
            }
        }
        return count;
    }
    public static void sortLogs(TransactionLog[] logs) {
        for (int i = 0; i < logs.length - 1; i++) {
            for (int j = 0; j < logs.length - i - 1; j++) {
                if (logs[j].accountId.compareTo(logs[j + 1].accountId) > 0) {
                    TransactionLog temp = logs[j];
                    logs[j] = logs[j + 1];
                    logs[j + 1] = temp;
                }
            }
        }
    }
    public static void printLogs(String label, TransactionLog[] logs) {
        System.out.print(label + ": [");
        for (int i = 0; i < logs.length; i++) {
            System.out.print(logs[i].accountId);
            if (i < logs.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        TransactionLog[] logs = {
                new TransactionLog("accB"),
                new TransactionLog("accA"),
                new TransactionLog("accB"),
                new TransactionLog("accC")
        };
        printLogs("Input logs", logs);
        linearSearchFirst(logs, "accB");
        linearSearchLast(logs, "accB");
        sortLogs(logs);
        printLogs("Sorted logs", logs);
        int[] holder = new int[1];
        int index = binarySearch(logs, "accB", holder);
        int count = countOccurrences(logs, "accB");
        System.out.println("Binary accB: index " + index + " (" + holder[0] + " comparisons), count=" + count);
    }
}
