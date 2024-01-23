package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Random random;
    static Map<Integer, Boolean> result1;       // Статистика для игрока, не меняющего свой выбор.
    static Map<Integer, Boolean> result2;       // Статистика для игрока, изменяющего свой выбор.
    static int doorCount;                      // Количество дверей.
    static int attempts;                         // Количество попыток.

    public static void main(String[] args) {
        random = new Random();
        result1 = new HashMap<>();
        result2 = new HashMap<>();
        doorCount = 3;
        attempts = 1000;

        for (int i = 0; i < attempts; i++) {     // Розыгрыш (1000 попыток).
            trial(i);
        }

        int win = 0;                             // Статистика для первого игрока, не меняющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry: result1.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.printf("\nИгрок, не меняющий выбор двери выиграл %d раз из %d попыток\n", win, attempts);

        win = 0;                                  // Статистика для второго игрока, изменяющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry: result2.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.printf("Игрок,меняющий выбор двери выиграл %d раз из %d попыток\n", win, attempts);
    }

    private static void trial(int numRound) {
        int success = random.nextInt(doorCount);
        int firstChoice = random.nextInt(doorCount);
        int freeOpenDoor = 0;
        int secondChoice = 0;

        for (int i = 0; i < doorCount; i++) {
            if (i != success && i != firstChoice){
                freeOpenDoor = i;
            }
        }

        for (int i = 0; i < doorCount; i++) {            // игрок не меняет свой выбор.
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = firstChoice;
            }
        }
        result1.put(numRound, success == secondChoice);   // статистика игрока, не меняющего выбор

        for (int i = 0; i < doorCount; i++) {            // игрок меняет свой выбор.
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = i;
            }
        }
        result2.put(numRound, success == secondChoice);   // статистика игрока, меняющего выбор
    }
}