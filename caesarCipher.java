import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class caesarCipher{
    public static void main(String args[]){
        // System.out.println("Hello Java");
        String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
        Scanner input = new Scanner(System.in);
        String text =  "",encrypt = "",decrypt;
        int key = 0;
        while(true){
            System.out.println("\nCaesar Cipher \n1. Read Plain Text and Key\n2. Encrypt\n3. Decrypt\nAny other - exit\n");
            int option = input.nextInt();
            if(option == 1){
                System.out.println("Enter text: ");
                text = input.next().concat(input.nextLine());
                System.out.println("Enter key: ");
                key = input.nextInt();
            }
            else if(option == 2){
                encrypt = "";
                for(int i=0;i<text.length();i++){
                    if(text.charAt(i)==' ')
                        encrypt+=' ';
                    else{
                        int charPos = ALPHABETS.indexOf(text.charAt(i));
                        int keyVal = (charPos+key)%26;
                        encrypt+=ALPHABETS.charAt(keyVal);
                    }
                }
                System.out.println("Encrypted text is: "+encrypt);
            }
            else if(option == 3){
                System.out.println("Enter text: ");
                text = input.next().concat(input.nextLine());
                System.out.println("Decrypting...");
                for(int k=0;k<26;k++){
                    decrypt = "";
                    for(int i=0;i<text.length();i++){
                        if(text.charAt(i)==' ')
                            decrypt+=' ';
                        else{
                            int charPos = ALPHABETS.indexOf(text.charAt(i));
                            int keyVal = (charPos-k)%26;
                            if(keyVal<0)
                                keyVal+=ALPHABETS.length();
                            decrypt+=ALPHABETS.charAt(keyVal);
                        }
                        
                    }
                    System.out.println("Decrypted text is: "+decrypt);
                }
                
            }
            else
                break;

        }
    }
}