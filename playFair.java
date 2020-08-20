import java.io.*;
import java.util.*;

public class playFair{

    public static void main(String args[]){
        // System.out.println("Hello Java");
        Scanner input = new Scanner(System.in);
        String text="",key="",encrypt="",decrypt="",mtext="";
        char[][] pMatrix = new char[5][5];
        while(true){
            System.out.println("\nCaesar Cipher \n1. Read Plain Text \n2. Read Key\n3. Construct Playfair Matrix\n4. Encrypt\n5. Decrypt\nAny other - exit\n");
            int option = input.nextInt();
            if(option == 1){
                System.out.println("Enter text: ");
                text = input.next().concat(input.nextLine());
                String t = text.replaceAll("\\s","");
                text=t;
                for(int i=0;i<text.length()-1;i+=2){
                    if(text.charAt(i)==text.charAt(i+1)){
                        String temp = text.substring(0, i+1);
                        temp+="x";
                        temp+=text.substring(i+1);
                        text=temp;
                    }
                }
                if(text.length()%2!=0)
                    text+="x";
                System.out.println(text);              
            }
            else if(option == 2){
                System.out.println("Enter key: ");
                key = input.next().concat(input.nextLine());
                String mtextdup = key+"abcdefghijklmnopqrstuvwxyz";
                mtext = "";
                for(int i=0;i<mtextdup.length();i++){
                    int j=0;
                    boolean dupflag = false;
                    while(j<i){
                        if(mtextdup.charAt(j)==mtextdup.charAt(i))
                            dupflag = true; 
                        j++;
                    }
                    if(!dupflag)
                        mtext+=mtextdup.charAt(i);
                }
                //System.out.println(mtext.length());
            }
            else if(option == 3){
                int c;
                System.out.println("Which letter to exclude? (1)i (2)j: ");
                c = input.nextInt();
                char ch ='j';
                if(c==1) ch='i';
                else if(c==2) ch='j';
                int k=0;
                for(int i=0;i<5;i++){
                    for(int j=0;j<5;j++){
                        char letter = mtext.charAt(k);
                        if(letter!=ch){
                            pMatrix[i][j]=mtext.charAt(k);
                            k++;
                        }
                        else{
                            j--;
                            k++;
                        }
                    }
                } 
                System.out.println("The Playfair Matrix for key "+key+" is: ");
                for(int i=0;i<5;i++){
                    for(int j=0;j<5;j++){
                        System.out.print(pMatrix[i][j]+" ");
                    }
                    System.out.println();
                }
            }
            else if(option == 4){
                encrypt = "";
                int frow = -1,fcol = -1,srow = -1,scol= -1;
                for(int i=0;i<text.length()-1;i+=2){
                    char fc = text.charAt(i);
                    char sc = text.charAt(i+1);
                    for(int a=0;a<5;a++){
                        for(int b=0;b<5;b++){
                            if(pMatrix[a][b]==fc){
                                frow = a;
                                fcol = b;
                            }
                            else if(pMatrix[a][b]==sc){
                                srow = a;
                                scol = b;
                            }
                        }
                    }
                    if(frow == srow){
                        fcol = (fcol+1)%5;
                        scol = (scol+1)%5;
                        if(fcol<0)
                            fcol+=5;
                        if(scol<0)
                            scol+=5;
                        encrypt+=pMatrix[frow][fcol];
                        encrypt+=pMatrix[srow][scol];
                    
                    }
                    else if(fcol == scol){
                        frow = (frow+1)%5;
                        srow = (srow+1)%5;
                        if(frow<0)
                            frow+=5;
                        if(srow<0)
                            srow+=5;
                        encrypt+=pMatrix[frow][fcol];
                        encrypt+=pMatrix[srow][scol];
                    
                    }
                    else{
                        encrypt+=pMatrix[frow][scol];
                        encrypt+=pMatrix[srow][fcol];
                    }
                }
                System.out.println("Encrypted text is: "+encrypt);
            }
            else if(option == 5){
                System.out.println("Enter text: ");
                text = input.next().concat(input.nextLine());
                decrypt="";
                int frow = -1,fcol = -1,srow = -1,scol= -1;
                for(int i=0;i<text.length()-1;i+=2){
                    char fc = text.charAt(i);
                    char sc = text.charAt(i+1);
                    for(int a=0;a<5;a++){
                        for(int b=0;b<5;b++){
                            if(pMatrix[a][b]==fc){
                                frow = a;
                                fcol = b;
                            }
                            else if(pMatrix[a][b]==sc){
                                srow = a;
                                scol = b;
                            }
                        }
                    }
                    if(frow == srow){
                        fcol = (fcol-1)%5;
                        scol = (scol-1)%5;
                        if(fcol<0)
                            fcol+=5;
                        if(scol<0)
                            scol+=5;
                        decrypt+=pMatrix[frow][fcol];
                        decrypt+=pMatrix[srow][scol];
                    
                    }
                    else if(fcol == scol){
                        frow = (frow-1)%5;
                        srow = (srow-1)%5;
                        if(frow<0)
                            frow+=5;
                        if(srow<0)
                            srow+=5;
                        decrypt+=pMatrix[frow][fcol];
                        decrypt+=pMatrix[srow][scol];
                    
                    }
                    else{
                        decrypt+=pMatrix[frow][scol];
                        decrypt+=pMatrix[srow][fcol];
                    }
                }
                System.out.println("Decrypted message with key "+key+" is: "+decrypt);
            }
            else
                break;
        }

    }

}