public class Analyzer
{
    /* Implementation of a linear search */
    public static int linearSearch(String[] dataSet, String element)
    {
        /* Iterate through all strings in dataSet */
        for (int index = 0; index < dataSet.length; index++)
        {
            /* Check for a match */
            if (element.equals(dataSet[index]))
                return index;
        }

        // If no index matches
        return -1;
    }

    /* Implementation of a binary search */
    public static int binarySearch(String[] dataSet, String element)
    {
        /* Index lies strictly between endpoints */
        int intervalStart = -1;
        int intervalEnd = dataSet.length;
        int index;

        /* Iterate through all strings in dataSet */
        while (intervalEnd - intervalStart > 1) // When the difference is only one, there are no possible values inside
        {
            /* Guess the index in the middle of the interval */
            index = intervalStart + (intervalEnd - intervalStart) / 2;

            /* Check for match */
            if (element.equals(dataSet[index]))
                return index;
            else
            {
                /* Since 'index' is not a match, it is safe to set the (exclusive) endpoint to that value */
                if (element.compareTo(dataSet[index]) > 0)
                    intervalStart = index;
                else
                    intervalEnd = index;
            }
        }

        // If loop does not return a match
        return -1;
    }

    /* Driver class */
    public static void main(String[] args)
    {
        /* Get the data */
        String[] data = StringData.getData();
        String[] searchArgs = {"not_here", "mzzzz", "aaaaa"};

        /* For each case, try a linear and binary search on it */
        for (String search : searchArgs)
        {
            System.out.print("ANALYSIS: " + search + "\n");

            long startLinear = System.nanoTime(); // Grab the starting time
            int linearFind = linearSearch(data, search);
            long stopLinear = System.nanoTime();  // Grab the stopping time
            System.out.print("Linear search\n" +
                    "  Result: " + linearFind + "\n" +
                    "  Time: " + (stopLinear - startLinear) + "\n");

            long startBinary = System.nanoTime();
            int binaryFind = binarySearch(data, search);
            long stopBinary = System.nanoTime();
            System.out.print("Binary search\n" +
                    "  Result: " + binaryFind + "\n" +
                    "  Time: " + (stopBinary - startBinary) + "\n");

            System.out.print("\n");
        }
    }
}