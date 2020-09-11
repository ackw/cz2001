import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KMP {
    public static void main(String args[]) throws IOException  {
        String file = ReadFile();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Substring comparision: ");
        String input = sc.nextLine();

        KMP(file, input);
    }

    public static String ReadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Testing.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (reader.readLine() != null) {
                stringBuilder.append(reader.readLine());
            }
            reader.close();
            // Remove the null characters
            stringBuilder.setLength(stringBuilder.length() - 4);
        } catch (FileNotFoundException e) {
            e.getMessage();
        } finally {
            return stringBuilder.toString();
        }
    }

    public static int[] cmp(String input) {
        int len = input.length();
        int []cmp = new int[len];
        int j = 0;

        for(int i=1; i<len; i++) {
            while(j > 0 && input.charAt(i) != input.charAt(j)) {
                j = cmp[j-1];
            }if(input.charAt(i) == input.charAt(j)) {
                cmp[i] = ++j;
            }
        }
        return cmp;
    }

    public static ArrayList<Integer> KMP(String text, String input) {
        long start = System.nanoTime();

        ArrayList<Integer> list = new ArrayList<>();

        int [] pi = cmp(input);
        int textLen = text.length();
        int inpLen = input.length();
        int j = 0;

        for(int i=0; i<textLen; i++) {
            while(j > 0 && text.charAt(i)!= input.charAt(j)) {
                j = pi[j-1];
            }if(text.charAt(i) == input.charAt(j)) {
                if(j== inpLen-1) {
                    list.add(i-inpLen+1);
                    j = pi[j];
                }else {
                    j++;
                }
            }
        }

        int size = list.size();
        System.out.println("Total found: " + size);

        for(int i=0; i<size; i++) {
            System.out.println("Pattern found in index: " + list.get(i)+1);
        }

        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println("Time Taken for KMP: " + elapsedTime);

        return list;
    }
}