import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Anagramme {

    public static boolean isAnagramme_First(String chaine1, String chaine2) {
        chaine1 = chaine1.replaceAll("\\s", "");
        chaine2 = chaine2.replaceAll("\\s", "");

        if (chaine1.length() != chaine2.length()) {
            return false;
        }

        for (char c1 : chaine1.toCharArray()) {
            boolean found = false;
            for (char c2 : chaine2.toCharArray()) {
                if (!found) {
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

        int[] minuscule_pool = new int[26];
        int[] majuscule_pool = new int[10];

        for (int i = 0; i < chaine2.length(); i++) {

            if (chaine1.charAt(i) >= 'a') minuscule_pool[chaine1.charAt(i) - 'a']++;
            if (chaine2.charAt(i) >= 'a') minuscule_pool[chaine2.charAt(i) - 'a']--;
            if (chaine1.charAt(i) < 'a') majuscule_pool[chaine1.charAt(i) - '0']++;
            if (chaine2.charAt(i) < 'a') majuscule_pool[chaine2.charAt(i) - '0']--;
        }
        for (int lettre : minuscule_pool) {
            if (lettre != 0) {
                return false;
            }
        }

        for (int lettre : majuscule_pool) {
            if (lettre != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        File mots = new File(args[0]);
        File dict = new File(args[1]);

        //File mots = new File("./src/words.txt");
        //File dict = new File("./src/dict.txt");

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


        for (String words : AllWords) {
            int found = 0;
            for (String words_in_dict : Dictionnary) {
                if (words_in_dict != null && isAnagramme_Second(words, words_in_dict)) {
                    found++;
                }
            }
            Anagramme_found.add(found);
        }


        for (int i = 0; i < AllWords.size(); i++) {
            System.out.println(AllWords.get(i) + " " + Anagramme_found.get(i));
        }
    }

}
