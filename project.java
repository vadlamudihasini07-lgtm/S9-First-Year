package mypackage;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// ---------------------------------------------
// Bin class: Represents a storage bin in warehouse
// ---------------------------------------------
class Bin {

    private int capacity;          // Maximum space a bin has
    private int remainingSpace;    // How much space is left
    private List<Integer> items;   // Items stored in the bin

    // Constructor
    public Bin(int capacity) {
        this.capacity = capacity;
        this.remainingSpace = capacity;
        this.items = new ArrayList<>();
    }

    // Try to add an item into this bin
    public boolean addItem(int size) {
        if (size <= remainingSpace) {
            items.add(size);             // Store item
            remainingSpace -= size;      // Reduce available space
            return true;
        }
        return false; // Item does not fit
    }

    public int getRemainingSpace() {
        return remainingSpace;
    }

    public List<Integer> getItems() {
        return items;
    }
}


// ---------------------------------------------------
// Main Warehouse Bin Packing Assistant
// ---------------------------------------------------
public class project {

    // This method arranges items into bins
    public static List<Bin> packItems(List<Integer> items, int binCapacity) {

        // STEP 1: Sort items from biggest to smallest
        Collections.sort(items, Collections.reverseOrder());

        List<Bin> bins = new ArrayList<>();

        // STEP 2: Try to place each item in a bin
        for (int item : items) {

            boolean placed = false;

            // Check existing bins
            for (Bin bin : bins) {
                if (bin.addItem(item)) {
                    placed = true;
                    break;
                }
            }

            // If item did not fit in any bin → create new bin
            if (!placed) {
                Bin newBin = new Bin(binCapacity);
                newBin.addItem(item);
                bins.add(newBin);
            }
        }

        return bins;
    }

    // ---------------------------------------------------
    // MAIN PROGRAM (Now takes input from user)
    // ---------------------------------------------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Warehouse Bin Packing Assistant ===");

        // Take bin capacity from user
        System.out.print("Enter bin capacity: ");
        int binCapacity = sc.nextInt();

        // Take number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        List<Integer> itemSizes = new ArrayList<>();

        // Take item sizes from user
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter size of item " + i + ": ");
            int size = sc.nextInt();
            itemSizes.add(size);
        }

        // Pack items
        List<Bin> resultBins = packItems(itemSizes, binCapacity);

        // Display bin arrangement
        int binNumber = 1;
        System.out.println("\n=== Packing Result ===");
        for (Bin bin : resultBins) {
            System.out.println("Bin " + binNumber + ": " + bin.getItems()
                    + " | Remaining Space: " + bin.getRemainingSpace());
            binNumber++;
        }

        sc.close();
    }
}
