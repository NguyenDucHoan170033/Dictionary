/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryModel {
    private Map<String, String> dictionary = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public void loadDictionary() {
        try {
            FileInputStream fileIn = new FileInputStream("dictionary.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dictionary = (Map<String, String>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
           
            dictionary = new HashMap<>();
        }
    }

    public void saveDictionary() {
        try {
            FileOutputStream fileOut = new FileOutputStream("dictionary.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dictionary);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addWord(String eng, String vi) {
    if (!dictionary.containsKey(eng)) {
        dictionary.put(eng, vi);
        return true;
    } else if (shouldUpdateWord()) {
        dictionary.put(eng, vi);
        return true;
    }
    return false;
}
    public boolean shouldUpdateWord() {
    System.out.println("Word already exists. Do you want to update its meaning? (Y/N)");
    String choice = sc.nextLine().toLowerCase();
    return choice.equals("y");
}

    public boolean removeWord(String eng) {
        if (dictionary.containsKey(eng)) {
            dictionary.remove(eng);
            return true;
        }
        return false;
    }

    public String translate(String eng) {
        return dictionary.get(eng);
    }
}
