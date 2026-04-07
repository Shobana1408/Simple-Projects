import java.util.Random;
class Asset {
    String name;
    double returnRate;
    double volatility;
    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}
class PortfolioReturnSorting {
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] leftArr = new Asset[n1];
        Asset[] rightArr = new Asset[n2];
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
            if (leftArr[i].returnRate <= rightArr[j].returnRate) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }
    public static void quickSortDesc(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDesc(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }
    public static int partitionDesc(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    public static Asset[] copyArray(Asset[] original) {
        Asset[] copy = new Asset[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Asset(original[i].name, original[i].returnRate, original[i].volatility);
        }
        return copy;
    }
    public static void printAssets(String label, Asset[] assets) {
        System.out.print(label + ": [");
        for (int i = 0; i < assets.length; i++) {
            System.out.print(assets[i].name + ":" + assets[i].returnRate + "%");
            if (i < assets.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 18),
                new Asset("TSLA", 8, 35),
                new Asset("GOOG", 15, 12)
        };
        System.out.print("Input: [");
        for (int i = 0; i < assets.length; i++) {
            System.out.print(assets[i].name + ":" + assets[i].returnRate + "%");
            if (i < assets.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        Asset[] mergeArray = copyArray(assets);
        Asset[] quickArray = copyArray(assets);
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        printAssets("Merge", mergeArray);
        quickSortDesc(quickArray, 0, quickArray.length - 1);
        printAssets("Quick (desc)", quickArray);
    }
}
