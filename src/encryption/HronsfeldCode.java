/*
 * Vitalii Chernetskyi
 * Encryption combining Transposition and Substitution methods
 * 07.11.2019
 * CS-522a
 * Faculty of Cybersecurity, Computer and Software Engineering
 * National Aviation University
 */

package encryption;

public class HronsfeldCode {

    public String codeHronsfeld(String plainText, String key, String todo){

        int plainTextLen = plainText.length();
        int keyLen = key.length();
        String result ="";

        char[] plainTextArr = plainText.toCharArray();
        char[] keyArr = key.toCharArray();

        for(int i = 0; i < keyLen; i++){
            if(keyArr[i] < 48 || keyArr[i] > 57){
                return "\nWarning! Key must be in range 0 - 9 !\nText can not be coded or decoded!\n";
            }
        }

        char[][] textKeyMatrix = new char[2][plainTextLen];

        textKeyMatrix[0] = plainTextArr.clone();

        int k = 0;
        for(int j = 0; j < plainTextLen; j++){
            if(k == keyLen){
                k = 0;
            }
            textKeyMatrix[1][j] = keyArr[k];
            k++;
        }

        if(todo.equals("code")){
            for(int j = 0; j < plainTextLen; j++){
                int incNum = Character.getNumericValue(textKeyMatrix[1][j]);
                for(int val = 0; val < incNum; val++){
                    textKeyMatrix[0][j]++;
                    if(textKeyMatrix[0][j] > 126){
                        textKeyMatrix[0][j] -= 95;
                    }
                }
                result += textKeyMatrix[0][j];
            }
        }
        else if(todo.equals("decode")){
            for(int j = 0; j < plainTextLen; j++){
                int incNum = Character.getNumericValue(textKeyMatrix[1][j]);
                for(int val = 0; val < incNum; val++){
                    textKeyMatrix[0][j]--;
                    if(textKeyMatrix[0][j] < 32){
                        textKeyMatrix[0][j] += 95;
                    }
                }
                result += textKeyMatrix[0][j];
            }
        }

        return result;

    }

}
