class Client {
    String name;
    int riskScore;
    double accountBalance;
    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }
}
class ClientRiskScoreRanking {
    public static void bubbleSortAscending(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        System.out.print("Bubble (asc): [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i].name + ":" + clients[i].riskScore);
            if (i < clients.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] // Swaps: " + swaps);
    }
    public static void insertionSortDescending(Client[] clients) {
        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && (
                    clients[j].riskScore < key.riskScore ||
                            (clients[j].riskScore == key.riskScore &&
                                    clients[j].accountBalance > key.accountBalance)
            )) {
                clients[j + 1] = clients[j];
                j--;
            }

            clients[j + 1] = key;
        }
        System.out.print("Insertion (desc): [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i].name + ":" + clients[i].riskScore);
            if (i < clients.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void printTopRisks(Client[] clients, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < topN && i < clients.length; i++) {
            System.out.print(clients[i].name + "(" + clients[i].riskScore + ")");
            if (i < topN - 1 && i < clients.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    public static Client[] copyArray(Client[] original) {
        Client[] copy = new Client[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Client(original[i].name, original[i].riskScore, original[i].accountBalance);
        }
        return copy;
    }
    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000)
        };
        System.out.print("Input: [");
        for (int i = 0; i < clients.length; i++) {
            System.out.print(clients[i].name + ":" + clients[i].riskScore);
            if (i < clients.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        Client[] bubbleArray = copyArray(clients);
        Client[] insertionArray = copyArray(clients);
        bubbleSortAscending(bubbleArray);
        insertionSortDescending(insertionArray);
        printTopRisks(insertionArray, 3);
    }
}
