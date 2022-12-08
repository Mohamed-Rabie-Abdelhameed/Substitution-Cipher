import java.util.*;

public class Main {
    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] alphabetCopy= "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] sub1 = shuffle(alphabetCopy);
    static char[] sub2 = shuffle(alphabetCopy);

    public static void main(String[] args) {
        System.out.println("Alphabet: " + Arrays.toString(alphabet));
        System.out.println("Sub1:     " + Arrays.toString(sub1));
        System.out.println("Sub2:     " + Arrays.toString(sub2));

        String plainText;
        String cipherText;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Plain Text: ");
        plainText = input.nextLine();
        plainText = plainText.toLowerCase();
        plainText = plainText.replaceAll(" ","");
        cipherText = encrypt(plainText);
        System.out.println("Encrypted Text: " + cipherText);
        System.out.println("Decrypted Text: " + decrypt(cipherText));
    }

    public static char[] shuffle(char[] alphabet) {
        char[] sub = new char[alphabet.length];
        Random rand = new Random();
        for (int i = 0; i < alphabet.length; i++) {
            int randomIndexToSwap = rand.nextInt(alphabet.length);
            int temp = alphabet[randomIndexToSwap];
            alphabet[randomIndexToSwap] = alphabet[i];
            alphabet[i] = (char) temp;
        }
        System.arraycopy(alphabet,0,sub,0,alphabet.length);
        return sub;
    }

    public static String encrypt(String plainText){
        char[] charArray = plainText.toCharArray();
        StringBuilder encrypted = new StringBuilder();
        for(int i=0; i<charArray.length; i++){
            int index = findIndex(charArray[i], alphabet);
            if(i%2 == 0){
                encrypted.append(sub1[index]);
            }
            else{
                encrypted.append(sub2[index]);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String cipherText){
        char[] charArray = cipherText.toCharArray();
        StringBuilder decrypted = new StringBuilder();
        for(int i=0; i<charArray.length; i++){
            int index;
            if(i%2 == 0){
                index = findIndex(charArray[i], sub1);
            }
            else{
                index = findIndex(charArray[i], sub2);
            }
            decrypted.append(alphabet[index]);
        }
        return decrypted.toString();
    }

    public static int findIndex(char letter, char[] alphabet){
        for(int i =0; i<alphabet.length; i++){
            if(letter == alphabet[i]){
                return i;
            }
        }
        return -1;
    }
}
