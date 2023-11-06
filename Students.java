import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A program to randomly select students for class activities.
 * Can select a number of students, or groups of a certain size.
 * For usage information run: <code>java Students help</code>
 */
class Students {
    // TODO: Add tests and make more testable
    private static ArrayList<String> students = new ArrayList<>();

    public static void main(String[] args) {
        // Hard-code the arguments
        // args = new String[] {"5"}; // Number of random students
        // args = new String[] {"help"}; // Print help message
        // args = new String[] {"groups", "3"}; // Group size

        readStudents("students.txt");
        Collections.shuffle(students);

        // The default action with no other arguments is to get 1 random student
        if (args.length == 0) {
            randomStudent(1);
            return;
        }
        
        int rngMax = -1;

        switch (args[0]) {
            case "groups":
                // Get group size
                if (args.length != 2) {
                    throw new IllegalArgumentException("Must specify group size.");
                }
                int groupSize = getNumber(args[1]);

                // Make sure group size is valid
                if (groupSize > students.size()) {
                    throw new IllegalArgumentException("Group size cannot be larger than number of students.");
                } else if (groupSize < 1) {
                    throw new IllegalArgumentException("Group size must be at least 1.");
                }

                groups(groupSize);
                break;
            case "-h":
            case "--help":
            case "usage":
            case "help":
                printHelp();
                break;
            case "coin":
                rngMax = 2;
            case "die":
                if (rngMax == -1) {
                    rngMax = getNumber(args[1]);
                }
                System.out.println((int) (Math.random() * rngMax) + 1);
                break;
            default:
                int numStudents;

                // if the argument is "all", list all students
                if (args[0].equals("all")) {
                    numStudents = students.size();
                } else {
                    numStudents = getNumber(args[0]);
                }

                // Make sure number of students is valid
                if (numStudents > students.size()) {
                    throw new IllegalArgumentException("Illegal number of students. For all students, use 'all'.");
                } else if (numStudents < 1) {
                    throw new IllegalArgumentException("Number of students must be at least 1");
                }

                randomStudent(numStudents);
                break;
        }
    }

    /**
     * Takes a string and returns the number it represents, throwing a more sensible exception if it's not a number.
     */
    private static int getNumber(String argument) {
        int num;
        try {
            num = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Expected a number; got: " + argument);
        }
        return num;
    }

    /**
     * Reads a file into the students array.
     */
    private static void readStudents(String filename) {
        // Read each line of students.txt into students array
        try (Scanner fileInput = new Scanner(new File(filename))) {
            while (fileInput.hasNextLine()) {
            students.add(fileInput.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Prints groups of students of a certain size.
     * Any remaining students are printed on their own line.
     * @param groupSize The number of students in each group.
     */
    private static void groups(int groupSize) {
        int index = 0;
        for (int i = 0; i < students.size(); i++) {
            System.out.print(students.get(i) + " ");
            index++;
            if (index == groupSize) {
                System.out.println();
                index = 0;
            } else if (i != students.size() - 1) {
                System.out.print("and ");
            }
        }
    }

    /**
     * Prints a given number of random students.
     * @param numStudents The number of students to print.
     */
    private static void randomStudent(int numStudents) {
        for (int i=0; i<numStudents; i++) {
            System.out.println(students.get(i));
        }
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  To get a random student: java Students");
        System.out.println("  To get a number of students: java Students [number of students]");
        System.out.println("  To get groups of a certain size: java Students groups [group size]");
        System.out.println("  To get a random number between 1 and 2: java Students coin");
        System.out.println("  To get a random number between 1 and n: java Students die n");
    }
}