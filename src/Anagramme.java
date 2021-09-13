
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagramme {

    public static boolean isAnagramme_First(String chaine1, String chaine2) {
        chaine1 = chaine1.replaceAll("\s", "");
        chaine2 = chaine2.replaceAll("\s", "");

        if (chaine1.length() != chaine2.length()) {
            return false;
        }

        for (char c1 : chaine1.toCharArray()) {
            boolean found = false;
            for (char c2 : chaine2.toCharArray()) {
                if(!found) {
                    if (c1 == c2) {
                        chaine2 = chaine2.replaceFirst(Character.toString(c2), Character.toString(Character.MIN_VALUE));
                        found = true;
                    }
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;

    }

    public static boolean isAnagramme_Second(String chaine1, String chaine2) {
        if (chaine1 == null || chaine2 == null) return false;

        chaine1 = chaine1.replaceAll("\\s", "");
        chaine2 = chaine2.replaceAll("\\s", "");

        if (chaine1.length() != chaine2.length()) return false;

        char[] charArray1 = chaine1.toCharArray();
        char[] charArray2 = chaine2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        for (int i = 0; i < charArray1.length; i++) {

            if (charArray1[i] != charArray2[i]) {
                return false;
            }

        }
        return true;

    }

    public static void testAlgorithmeSpeed() {
        long startTime1 = System.nanoTime();
        boolean resultat1 = isAnagramme_First("b  a  a d a", "aaabd");
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        boolean resultat2 = isAnagramme_Second("b  a  a d a", "aaabd");
        long endTime2 = System.nanoTime();

        System.out.println("First algorithme : " + resultat1 + " " + (double)(endTime1 - startTime1)/1000000 + " ms");
        System.out.println("Second algorithme : " + resultat2 + " " + (double)(endTime2 - startTime2)/1000000 + " ms");
    }

    public static void main(String[] args) throws Exception {

        //testAlgorithmeSpeed();

//        File mots = new File(args[0]);
//        File dict = new File(args[1]);

        File mots = new File("./src/words.txt");
        File dict = new File("./src/dict.txt");

        List<String> Dictionnary = new ArrayList<>();
        List<String> AllWords = new ArrayList<>();
        List<Integer> Anagramme_found = new ArrayList<>();

        InputStreamReader dict_reader = new InputStreamReader(new FileInputStream(dict));
        InputStreamReader mots_reader = new InputStreamReader(new FileInputStream(mots));

        BufferedReader Dict_br = new BufferedReader(dict_reader);
        BufferedReader mots_br = new BufferedReader(mots_reader);

        String line = null;
        while ((line = Dict_br.readLine()) != null) {
            Dictionnary.add(line);
        }
        line = null;
        while ((line = mots_br.readLine()) != null) {
            AllWords.add(line);
        }

        long startTime1 = System.nanoTime();
        for (String words : AllWords) {
            int found = 0;
            for (String words_in_dict : Dictionnary) {
                if (words_in_dict != null && isAnagramme_First(words, words_in_dict)) {
                    found++;
                }
            }
            Anagramme_found.add(found);
        }
        long endTime1 = System.nanoTime();

        for (int i = 0; i < AllWords.size(); i++) {
            System.out.println(AllWords.get(i) + " " + Anagramme_found.get(i));
        }

        System.out.println("Second algorithme : " + (double)(endTime1 - startTime1)/1000000 + " ms");

    }

}
