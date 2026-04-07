import java.util.Arrays;
class Trade {
    String id;
    int volume;
    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }
}
class HistoricalTradeVolumeAnalysis {
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Trade[] leftArr = new Trade[n1];
        Trade[] rightArr = new Trade[n2];
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].volume <= rightArr[j].volume) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    public static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDesc(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }
    public static int partitionDesc(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    public static Trade[] mergeTwoSortedLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        return result;
    }
    public static int totalVolume(Trade[] trades) {
        int total = 0;
        for (Trade t : trades) {
            total += t.volume;
        }
        return total;
    }
    public static Trade[] copyArray(Trade[] original) {
        Trade[] copy = new Trade[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Trade(original[i].id, original[i].volume);
        }
        return copy;
    }
    public static void printTrades(String label, Trade[] trades) {
        System.out.print(label + ": [");
        for (int i = 0; i < trades.length; i++) {
            System.out.print(trades[i].id + ":" + trades[i].volume);
            if (i < trades.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };
        System.out.print("Input: [");
        for (int i = 0; i < trades.length; i++) {
            System.out.print(trades[i].id + ":" + trades[i].volume);
            if (i < trades.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        Trade[] mergeArray = copyArray(trades);
        Trade[] quickArray = copyArray(trades);
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        printTrades("MergeSort", mergeArray);
        quickSortDesc(quickArray, 0, quickArray.length - 1);
        printTrades("QuickSort (desc)", quickArray);
        Trade[] morning = {
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };
        Trade[] afternoon = {
                new Trade("trade3", 500)
        };
        Trade[] merged = mergeTwoSortedLists(morning, afternoon);
        System.out.println("Merged morning+afternoon total: " + totalVolume(merged));
    }
}
