import java.util.Arrays;

public class Pakudex
{
    private Pakuri[] pakudex;
    private int currentFill = 0;
    private int maxFill;

    public Pakudex()
    {
        pakudex = new Pakuri[20];
        this.maxFill = 20;
    }

    public Pakudex(int capacity)
    {
        pakudex = new Pakuri[capacity];
        this.maxFill = capacity;
    }

    public int getSize()
    {
        return this.currentFill;
    }

    public int getCapacity()
    {
        return this.maxFill;
    }

    public String[] getSpeciesArray()
    {
        if (currentFill == 0)
            return null;

        String[] pakuriString = new String[currentFill];
        for (int pakuriNo = 0; pakuriNo < currentFill; pakuriNo++)
        {
            pakuriString[pakuriNo] = pakudex[pakuriNo].getSpecies();
        }

        return pakuriString;
    }

    public int[] getStats(String species)
    {
        int[] stats = new int[3];

        for (int pakuriNo = 0; pakuriNo < currentFill; pakuriNo++)
        {
            if (species.equals(pakudex[pakuriNo].getSpecies()))
            {
                Pakuri pakuriMatch = pakudex[pakuriNo];

                stats[0] = pakuriMatch.getAttack();
                stats[1] = pakuriMatch.getDefense();
                stats[2] = pakuriMatch.getSpeed();

                return stats;
            }
        }

        /* If no pakuri match, default to null */
        return null;
    }

    public void sortPakuri()
    {
        /* Start by getting a list of all current pakuri and cloning the current 'dex */
        String[] pakuriList = this.getSpeciesArray();
        Pakuri[] dexClone = this.pakudex.clone();

        /* Sort the list separately */
        String[] sortedList = pakuriList.clone();
        Arrays.sort(sortedList);

        /* Solve for the inverse permutation of the sort */
        int[] permutation = new int[this.currentFill];
        for (int i = 0; i < this.currentFill; i++)
        {
            for (int j = 0; j < this.currentFill; j++)
            {
                if (sortedList[i].equals(pakuriList[j]))
                    permutation[i] = j;
            }
        }

        /* Sort dexClone with the same permutation that sortedList was */
        for (int i = 0; i < this.currentFill; i++)
        {
            dexClone[i] = pakudex[permutation[i]];
        }

        /* Shove the sorted list back into the real pakudex */
        pakudex = dexClone;
    }

    public boolean addPakuri(String species)
    {
        /* Check for duplication */
        for (int pakuriNo = 0; pakuriNo < currentFill; pakuriNo++)
        {
            if (species.equals(pakudex[pakuriNo].getSpecies()))
            {
                return false;
            }
        }

        /* Slot new pakuri species into 'dex and return true for success */
        pakudex[currentFill] = new Pakuri(species);
        currentFill += 1;
        return true;
    }

    public boolean evolveSpecies(String species)
    {
        for (int pakuriNo = 0; pakuriNo < currentFill; pakuriNo++)
        {
            if (species.equals(pakudex[pakuriNo].getSpecies()))
            {
                pakudex[pakuriNo].evolve();
                return true;
            }
        }

        /* If species not in 'dex return false */
        return false;
    }
}
