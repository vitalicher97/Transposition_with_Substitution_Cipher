/*
 * Vitalii Chernetskyi
 * Encryption combining Transposition and Substitution methods
 * 07.11.2019
 * CS-522a
 * Faculty of Cybersecurity, Computer and Software Engineering
 * National Aviation University
 */

package encryption;

import java.util.*;

public class TranspCipher {

    public String transpCipherEnc(String initData, String key, String flag){
        // Flag variable to define encode or decode is needed

        String[] keyArr = key.split("|");
        String[] dataArr = initData.split("|");
        int keyLen = keyArr.length; // For matrix first side length
        int dataLen = dataArr.length;
        int textLen = (dataLen / keyLen); // Calculated for matrix second side length
        LinkedHashMap<String, String[]> dataMatrix = new LinkedHashMap<>();
        String resultText = "";

        // For creating array with enough length
        if(dataLen % keyLen != 0){
            textLen++;
        }

        // Defining matrix size for inputted text
        String[][] textMatrix = new String[keyLen][textLen];

        // Creating matrix that contains text for future encrypting
        int dataArrIndex = 0;
        for(int j = 0; j < textLen; j++){
            for(int i = 0; i < keyLen; i++){
                if(dataArrIndex < dataLen){
                    textMatrix[i][j] = dataArr[dataArrIndex];
                    dataArrIndex++;
                }
            }
        }


        // Map's keys correspond to the inputted keys. Keys corresponds with text matrix rows.
        int letter = 1;
        for(int i = 0; i < keyLen;i++){
            keyArr[i] = keyArr[i] + letter;
            letter++;
        }

        String[] initKeyArr = keyArr.clone(); // Clone of initial state of keyArr is needed for decoding

        if(flag.equals("decode")){
            Arrays.sort(keyArr);
        }
        for(int i = 0; i < keyLen;i++){
            dataMatrix.put(keyArr[i], textMatrix[i]);
        }


        // Keys sorted for letters misplacing. Main feature of transposition cipher encoding method.
        Arrays.sort(keyArr);

        if(flag.equals("decode")){
            keyArr = initKeyArr;
        }

        // Assign new letters sequence to the text matrix.
        int temp = 0;
        for(String k : keyArr){
            textMatrix[temp] = dataMatrix.get(k);
            temp++;
        }

        for(int j = 0; j < textLen; j++){
            for(int i = 0; i < keyLen; i++){
                if(textMatrix[i][j] != null){
                    resultText = resultText + textMatrix[i][j];
                }
                else {
                    resultText = resultText + " "; // Fills empty matrix cells. Necessary for correct decoding
                }
            }
        }


        return resultText;


    }

}