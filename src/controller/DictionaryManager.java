/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.DictionaryModel;

import view.Menu;

public class DictionaryManager extends Menu<Object> {

    private DictionaryModel model;
    private Scanner sc = new Scanner(System.in);

    public DictionaryManager() {
          this.model = new DictionaryModel();
         
        String[] menuOptions = {
            "Add Word",
            "Delete Word",
            "Traslate",
            "Exit"
        };
        super.title = "Worker Management System";
        super.mChon = new ArrayList<>(Arrays.asList(menuOptions));
    }
      
    

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                addWord();
                break;
            case 2:
                deleteWord();
                break;
            case 3:
                translateWord();
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


private void addWord() {
    if (model == null) {
        System.err.println("DictionaryModel is not initialized.");
        return;
    }
    System.out.println("Enter English word: ");
    String engWord = sc.nextLine();
    System.out.println("Enter Vietnamese word: ");
    String viWord = sc.nextLine();

    if (model.addWord(engWord, viWord)) {
        model.saveDictionary(); 
        System.out.println("Word added successfully.");
    } else if (model.shouldUpdateWord()) {
        model.addWord(engWord, viWord);
        model.saveDictionary(); 
        System.out.println("Word updated successfully.");
    }
}

private void deleteWord() {
    System.out.println("Enter English word to delete: ");
    String engWord = sc.nextLine();

    if (model.removeWord(engWord)) {
        System.out.println("Word deleted successfully.");
    } else {
        System.err.println("Word not found in the dictionary.");
    }
}

private void translateWord() {
    System.out.println("Enter English word to translate: ");
    String engWord = sc.nextLine();
    String viTranslation = model.translate(engWord);
    if (viTranslation != null) {
        System.out.println("Vietnamese translation: " + viTranslation);
    } else {
        System.err.println("Word not found in the dictionary");
    }
}
}