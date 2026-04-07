import java.util.ArrayList;
class Transaction {
    String id;
    double fee;
    String timestamp;
    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }
    public String toFeeString() {
        return id + ":" + fee;
    }
    public String toFeeTimeString() {
        return id + ":" + fee + "@" + timestamp;
    }
}
class TransactionFeeSortingAuditCompliance {
    public static void bubbleSortByFee(ArrayList<Transaction> transactions) {
        int n = transactions.size();
        int swaps = 0;
        int passes = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.print("BubbleSort (fees): [");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.print(transactions.get(i).toFeeString());
            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] // " + passes + " passes, " + swaps + " swaps");
    }
    public static void insertionSortByFeeAndTimestamp(ArrayList<Transaction> transactions) {
        for (int i = 1; i < transactions.size(); i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            while (j >= 0 && (
                    transactions.get(j).fee > key.fee ||
                            (transactions.get(j).fee == key.fee &&
                                    transactions.get(j).timestamp.compareTo(key.timestamp) > 0)
            )) {
                transactions.set(j + 1, transactions.get(j));
                j--;
            }

            transactions.set(j + 1, key);
        }
        System.out.print("InsertionSort (fee+ts): [");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.print(transactions.get(i).toFeeTimeString());
            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void findHighFeeOutliers(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> outliers = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }
        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.print("High-fee outliers: [");
            for (int i = 0; i < outliers.size(); i++) {
                System.out.print(outliers.get(i).toFeeString());
                if (i < outliers.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
    public static ArrayList<Transaction> copyList(ArrayList<Transaction> original) {
        ArrayList<Transaction> copy = new ArrayList<>();
        for (Transaction t : original) {
            copy.add(new Transaction(t.id, t.fee, t.timestamp));
        }
        return copy;
    }
    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        System.out.println("Input transactions:");
        for (Transaction t : transactions) {
            System.out.println(t.id + ", fee=" + t.fee + ", ts=" + t.timestamp);
        }
        ArrayList<Transaction> bubbleList = copyList(transactions);
        ArrayList<Transaction> insertionList = copyList(transactions);
        bubbleSortByFee(bubbleList);
        insertionSortByFeeAndTimestamp(insertionList);
        findHighFeeOutliers(transactions);
    }
}
