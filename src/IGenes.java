import java.util.ArrayList;
import java.util.Arrays;

public interface IGenes {
     ArrayList<String> FindGenes();
     boolean IsValidGen(int startIndex, int endIndex);
     int FindStartCodon(String dna, int searchFromIndex, int endCodonIndex);
     int FindEndCodon(String dna, int searchFromIndex);
}
