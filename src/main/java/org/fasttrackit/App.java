package org.fasttrackit;
/*2. In a company’s hierarchy, some employees manage other employees, who can in turn manage other employees.
 The input (as a string) format for the hierarchy is (name, sub-hierarchy_1, sub-hierarchy_2, … , sub-hierarchy_n),
  with every sub-hierarchy having the same format, recursively. Your task is to write code that parses the hierarchy,
  removes employees that are marked as “Unavailable” along with all the employees they manage and prints the resulting hierarchy.Input:
(“John”, (“Jasmine”, (“Jay”), (“Unavailable”)), (“Unavailable”, (“Jack”, (“Jeremy”))), (“Johanna”))Output:
(“John”, (“Jasmine”, (“Jay”)), (“Johanna”))
*/
public class App {

    private static final char LEFT_PARANTHESIS = '(';
    private static final char INVERTED_COMMA = '"';
    private static final char RIGHT_PARANTHESIS = ')';

    public static void main(String[] args) {

        // Hardcode this for now
        String input = "(\"John\", (\"Jasmine\", (\"Jay\"), (\"Unavailable\")), (\"Unavailable\", (\"Jack\", (\"Jeremy\"))), (\"Johanna\"))";
        // Better is to take from args[0]
//        String input = args[0];

        final Company company = new Company();
        // Remove the first and last paranthesis
        parseInput(input.substring(1, input.length() - 1), company.getHierarchy());

        removeFromCompany(company);

        // Print the solution
        printSolution(company);
    }

  
    private static void removeFromCompany(Company company) {

    }

    private static void parseInput(final String str, final Hierarchy hierarchy) {
        // Start from 1 to remove the fist inverted comma from getting matched
        int lastInvertedCommaOfName = str.indexOf(INVERTED_COMMA, 1);

        final String ownerEmp = str.substring(1, lastInvertedCommaOfName);
        hierarchy.setManager(new Employee(ownerEmp));

        int hierarchyStart = str.indexOf(LEFT_PARANTHESIS, lastInvertedCommaOfName);

        // Set not empty list of hierarchies from parsing when hierarchyStart != -1
        if (hierarchyStart != -1) {
            do {
                int endHierarchyChar = getHierarchyEndChar(hierarchyStart, str);

                final Hierarchy parseHierarchy = new Hierarchy();
                parseInput(str.substring(hierarchyStart + 1, endHierarchyChar), parseHierarchy);

                // Add the hierarchy
                hierarchy.addHierarchy(parseHierarchy);

                // Go further for additional hierarchies
                hierarchyStart = str.indexOf(LEFT_PARANTHESIS, endHierarchyChar);
            } while (hierarchyStart != -1);
        }
    }

    /**
     * Returns the matching paranthesis of the current hierarchy start paranthesis
     * startChar is the first character after '('
     */
    private static int getHierarchyEndChar(int startChar, String input) {

        // Tracks the number of open paranthesis
        int level = 1;  // first open paranthesis means level 1

        // Handle only start and end paranthesis.
        // Whenever we find the matching end paranthesis of the first open paranthesis we exit

        int position = startChar;
        // We use infinite loop to not have to use two return statements,
        // JVM knows this can either loop forever or return on matching paranthesis
        while (true) {
            char c = input.charAt(position);
            if (c == LEFT_PARANTHESIS) { //
                level++;
            } else if (c == RIGHT_PARANTHESIS) {
                level--;
                if (level == 1) {
                    return position;
                }
            }
            position++;
        }
    }

    private static void printSolution(Company company) {
        System.out.println(company.toString());
    }

}
