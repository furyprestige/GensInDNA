import com.sun.jdi.VMMismatchException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alonzo Estévez
 * @version 1.0
 * Class were you can store possible valid DNA Strings.
 * Some parameters can be changed, see the instance variables.
 */
public class DNA implements IGenes {
    public String dna;
    private ArrayList<String> genes = new ArrayList<>();
    //Change this parameter to accept another letters as valid nucleotides.
    private final List ValidNucleotides = new ArrayList<Character>(Arrays.asList('A','T','G','C'));
    //Change this parameter to accept another open codons.
    private final ArrayList<String> openCodons = new ArrayList<>(Arrays.asList("ATG"));
    //Change this parameter to accept another stop codons.
    private final ArrayList<String> closeCodons = new ArrayList(Arrays.asList("TAA","TAG","TGA"));
    private final int lengthCodons = 3;
    /**
     * Stores your String as a DNA Object.
     * A DNA string must have only valid nucleotides which are A,T,G and C.
     * If your DNA has other letters, they will be automatically deleted to make your DNA string valid.
     * your DNA string will be stored cleaned and in uppercase.
     * @param dna the dna string to store as an instance.
     */
    public DNA(String dna){
        dna = dna.toUpperCase();
        if (hasOnlyValidNucleotides(dna)){
            this.dna = dna;
        }
        else {
            dna = cleanDNA(dna,invalidNucleotides(dna));
            System.out.println("Created: "+dna);
            this.dna = dna;
        }
    }

    private ArrayList<String>invalidNucleotides(String dna){
        ArrayList<String> invalidNucleotides = new ArrayList<>();
        char character;
        for (int i = 0; i < dna.length() ; i++) {
            character = dna.charAt(i);
            if (!ValidNucleotides.contains(character)){
                if (!invalidNucleotides.contains(character)){
                    invalidNucleotides.add(String.valueOf(character));
                }
            }
        }
        return invalidNucleotides;
    }

    private String cleanDNA(String dna, ArrayList<String> invalidNucleotides){
        for (String caracter: invalidNucleotides) {
            dna = dna.replaceAll(caracter,"");
        }
        return dna;
    }

    private boolean hasOnlyValidNucleotides(String dna){
        char character;
        for (int i = 0; i < dna.length() ; i++) {
            character = dna.charAt(i);
            if (!ValidNucleotides.contains(character)){
                return false;
            }
        }
        return true;
    }

    /**
     * Analyses the DNA string in search of valid genes.
     * Once finished, additionally will store the Arraylist in the instance, call getArraylistGens to obtain it.
     * A gen is valid if the nucleotides between the start and end codon are divisible by 3.
     * @return an Arraylist containing all the valid gens in the DNA string.
     */
    @Override
    public ArrayList<String> FindGenes() {
        int startIndexGen,endIndexGen,searchEndFromIndex = 0,searchStartFromIndex = 0;
        ArrayList<String> genes = new ArrayList<>();
        String gen = "",dna = this.dna;
        endIndexGen = FindEndCodon(this.dna,searchEndFromIndex);
        while (endIndexGen != -1){
           startIndexGen = FindStartCodon(dna,searchStartFromIndex,endIndexGen);
            if (startIndexGen == -1){
                endIndexGen = FindEndCodon(dna,endIndexGen+1);
            }
            else{
                gen = dna.substring(startIndexGen,endIndexGen+lengthCodons);
                if (IsValidGen(startIndexGen,endIndexGen)){
                    genes.add(gen);

                }
                endIndexGen = FindEndCodon(dna,endIndexGen+1);
            }

        }
        this.genes.addAll(genes);
        return genes;


    }

    @Override
    public boolean IsValidGen(int startIndex, int endIndex) {
        startIndex +=2;
        int nucleotidsInGen = endIndex-startIndex-1;
        if (nucleotidsInGen % 3 == 0 && nucleotidsInGen != 0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int FindStartCodon(String dna, int searchFromIndex,int endCodonIndex) {
        int foundStartCodon, min=-1,finalMin = -1;
        String searchIn = dna.substring(searchFromIndex,endCodonIndex+1);
        boolean first = true;
            for (String codon:openCodons) {
                foundStartCodon = searchIn.lastIndexOf(codon)+searchFromIndex;
                if (foundStartCodon != -1){
                    if (first){
                        min = endCodonIndex - foundStartCodon;
                        finalMin = foundStartCodon;
                        first = false;
                    }
                    if (endCodonIndex - foundStartCodon < min){
                        finalMin = foundStartCodon;
                    }
                }
            }
            return finalMin;
    }

    @Override
    public int FindEndCodon(String dna, int searchFromIndex) {
        int foundEndCodon,min = -1;
        boolean first = true;
        for (String codon:closeCodons) {
            foundEndCodon = dna.indexOf(codon, searchFromIndex);
            if (foundEndCodon != -1) {
                if (first){
                    min = foundEndCodon;
                    first = false;
                }
                if ( foundEndCodon < min){
                    min = foundEndCodon;
                }
            }
        }
        return min;
    }

    /**
     * Be sure to call FindGenes at least once.
     * @return the Arraylist of gens stored in the instance.
     */
    public ArrayList<String> getGenes(){
        return this.genes;
    }
}


