package edu.computing.of.school.usm.jon.videopoker;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Created by jon on 4/7/2015.
 * class to initialize the deck and return
 * a shuffle stack of strings
 */
public class Card_Class {
    private Context context;
    public final String numbers[] = {
    "one","two","three","four","five","six","seven","eight","nine",
    "ten","eleven","twelve","thirtteen"
    };

    public final String card_type[] = {
       "hearts","diamonds","clubs","spades"
    };

    public Stack<String> new_game(){
        ArrayList<String> cards = new ArrayList<String>();
        Stack<String> shuffled_cards = new Stack<String>();
        for(int i = 0; i< 4; i++){
            for(int j = 0; j < 13; j++){
                switch(i){
                    case 0:
                           cards.add(numbers[j]+"_of_"+card_type[i]);
                        break;
                    case 1:
                            cards.add(numbers[j]+"_of_"+card_type[i]);
                        break;
                    case 2:
                             cards.add(numbers[j]+"_of_"+card_type[i]);
                        break;
                    case 3:
                            cards.add(numbers[j]+"_of_"+card_type[i]);
                        break;
                    default:
                        System.out.println("out of bounds");
                }
            }
        }

        Collections.shuffle(cards);

        for(int i = 0; i<52; i++)
            shuffled_cards.add(cards.get(i));

        return shuffled_cards;
    }

}
