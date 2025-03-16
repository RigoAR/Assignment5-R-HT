public class DoubleHashing {
    private Integer[] table;
    private int size;
    private int itemCount;

    public DoubleHashing(int size) {
        this.size = size;
        this.table = new Integer[size];
    }
    private int h1(int key) {
        int x = (key + 19) * (key + 11);
        x = x / 15;
        x = x + key;
        x = x % size;
        return x;
    }

    private int reverse(int value) {
        return Integer.parseInt(new StringBuilder(Integer.toString(value)).reverse().toString());
    }

    public String insert(int key) {
        int homeSlot = h1(key);
        StringBuilder result = new StringBuilder("Key " + key + ": home slot = " + homeSlot);

        if (table[homeSlot] == null || table[homeSlot] == key) {
            table[homeSlot] = key;
            itemCount += (table[homeSlot] == null) ? 1 : 0;
            return result.toString();
        }

        int step = reverse(key) % size;
        if (step == 0) step = 1;

        int collisions = 1;
        StringBuilder probeSeq = new StringBuilder();

        for (int i = 1; i < size; i++) {
            int slot = (homeSlot + i * step) % size;
            probeSeq.append(slot).append(" ");

            if (table[slot] == null || table[slot] == key) {
                table[slot] = key;
                itemCount += (table[slot] == null) ? 1 : 0;
                return result.append(", collisions = " + collisions +
                        ", probe sequence = [" + probeSeq.toString().trim() + "]").toString();
            }
            collisions++;
        }

        return result.append(" (table full)").toString();
    }

    public double getLoadFactor() {
        return (double) itemCount / size;
    }

    public DoubleHashing resize() {
        DoubleHashing newTable = new DoubleHashing(size * 2);
        for (Integer key : table) {
            if (key != null) {
                newTable.insert(key);
            }
        }
        return newTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(table[i] == null ? "null" : table[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        int[] keys = {25, 14, 9, 7, 5, 3, 0, 21, 6, 33, 25, 42, 24, 107};
        DoubleHashing hashTable = new DoubleHashing(13);

        System.out.println("Initial table (size " + hashTable.size + "): " + hashTable);

        for (int key : keys) {
            if (hashTable.getLoadFactor() >= 0.7) {
                System.out.println("\nRESIZING to " + (hashTable.size * 2));
                hashTable = hashTable.resize();
            }

            String insertResult = hashTable.insert(key);
            System.out.println(insertResult);
            System.out.println("Table: " + hashTable);
        }
    }
}