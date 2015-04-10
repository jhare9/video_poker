package edu.computing.of.school.usm.jon.videopoker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by jon on 4/7/2015.
 */
public class Game_Fragment  extends Fragment{
    private Button held_1;
    private Button held_2;
    private Button held_3;
    private Button held_4;
    private Button held_5;
    private Card_Class card_class;
    private ImageButton card_1;
    private ImageButton card_2;
    private ImageButton card_3;
    private ImageButton card_4;
    private ImageButton card_5;
    private TextView poker_hand;
    private Button deal;
    private Button bet;
    private Button bet_max;
    private Button credit_title;
    private TextView make_bet;
    private Stack<String> cards;
    private String[] cards_in_play;
    private int deal_count = 0;
    private int bet_count = 0;
    private boolean Ace_high_card = false;
    private int credits;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_poker_game,container,false);
        credits = 100;
        card_1 = (ImageButton) view.findViewById(R.id.card_one);
        card_2 = (ImageButton) view.findViewById(R.id.card_two);
        card_3 = (ImageButton) view.findViewById(R.id.card_three);
        card_4 = (ImageButton) view.findViewById(R.id.card_four);
        card_5 = (ImageButton) view.findViewById(R.id.card_five);

        held_1 = (Button) view.findViewById(R.id.held_one);
        held_2 = (Button) view.findViewById(R.id.held_two);
        held_3 = (Button) view.findViewById(R.id.held_three);
        held_4 = (Button) view.findViewById(R.id.held_four);
        held_5 = (Button) view.findViewById(R.id.held_five);

        bet = (Button) view.findViewById(R.id.bet);
        bet_max = (Button)view.findViewById(R.id.max_bet);
        credit_title = (Button)view.findViewById(R.id.credit);
        make_bet = (TextView)view.findViewById(R.id.enter_bet);

        poker_hand = (TextView)view.findViewById(R.id.poker_hand);

        deal = (Button) view.findViewById(R.id.deal);

        card_class = new Card_Class();
        cards = new Stack<String>();
        cards_in_play = new String[5];




            card_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deal_count > 0) {
                        if (held_1.getVisibility() == View.INVISIBLE)
                            held_1.setVisibility(View.VISIBLE);
                        else
                            held_1.setVisibility(View.INVISIBLE);
                    }
                }
            });

            card_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deal_count > 0) {
                        if (held_2.getVisibility() == View.INVISIBLE)
                            held_2.setVisibility(View.VISIBLE);
                        else
                            held_2.setVisibility(View.INVISIBLE);
                    }
                }
            });

            card_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deal_count > 0) {
                        if (held_3.getVisibility() == View.INVISIBLE)
                            held_3.setVisibility(View.VISIBLE);
                        else
                            held_3.setVisibility(View.INVISIBLE);
                    }
                }
            });

            card_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deal_count > 0) {
                        if (held_4.getVisibility() == View.INVISIBLE)
                            held_4.setVisibility(View.VISIBLE);
                        else
                            held_4.setVisibility(View.INVISIBLE);
                    }
                }
            });

            card_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deal_count > 0) {
                        if (held_5.getVisibility() == View.INVISIBLE)
                            held_5.setVisibility(View.VISIBLE);
                        else
                            held_5.setVisibility(View.INVISIBLE);
                    }
                }
            });

            bet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bet_count++;

                    poker_hand.setText("bet: "+bet_count);
                    make_bet.setVisibility(View.GONE);
                }
            });

            bet_max.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bet_count = 5;
                    poker_hand.setText("bet: "+bet_count);
                    make_bet.setVisibility(View.GONE);
                }
            });

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bet_count > 0) {

                    credit_title.setText("credits: " + credits);

                    if (credits == 0) {
                        credits = 25;
                        credit_title.setText("credits: " + credits);
                    }

                    deal_count++;

                    if (deal_count == 1) {
                        credits -= bet_count;
                        credit_title.setText("credits: " + credits);
                    }

                    deal.setText("draw");

                    poker_hand.setText(" ");

                    if (cards.size() < 5)
                        cards = card_class.new_game();


                    String temp = " ";

                    for (int i = 0; i < 5; i++) {
                        switch (i) {
                            case 0:
                                if (held_1.getVisibility() == View.INVISIBLE) {
                                    temp = cards.pop();
                                    cards_in_play[i] = temp;
                                    card_1.setBackgroundResource(getResources().getIdentifier(temp, "drawable", getActivity().getApplicationInfo().packageName));
                                }
                                held_1.setVisibility(View.INVISIBLE);
                                break;
                            case 1:
                                if (held_2.getVisibility() == View.INVISIBLE) {
                                    temp = cards.pop();
                                    cards_in_play[i] = temp;
                                    card_2.setBackgroundResource(getResources().getIdentifier(temp, "drawable", getActivity().getApplicationInfo().packageName));
                                }
                                held_2.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                if (held_3.getVisibility() == View.INVISIBLE) {
                                    temp = cards.pop();
                                    cards_in_play[i] = temp;
                                    card_3.setBackgroundResource(getResources().getIdentifier(temp, "drawable", getActivity().getApplicationInfo().packageName));
                                }
                                held_3.setVisibility(View.INVISIBLE);
                                break;
                            case 3:
                                if (held_4.getVisibility() == View.INVISIBLE) {
                                    temp = cards.pop();
                                    cards_in_play[i] = temp;
                                    card_4.setBackgroundResource(getResources().getIdentifier(temp, "drawable", getActivity().getApplicationInfo().packageName));
                                }
                                held_4.setVisibility(View.INVISIBLE);
                                break;
                            case 4:
                                if (held_5.getVisibility() == View.INVISIBLE) {
                                    temp = cards.pop();
                                    cards_in_play[i] = temp;
                                    card_5.setBackgroundResource(getResources().getIdentifier(temp, "drawable", getActivity().getApplicationInfo().packageName));
                                }
                                held_5.setVisibility(View.INVISIBLE);
                                break;
                        }
                    }

                    switch (kinds_and_pairs_and_fullhouse(cards_in_play)) {
                        case "pair":
                            poker_hand.setText("pair");
                            break;
                        case "two pair":
                            poker_hand.setText("two pair");
                            break;
                        case "three of a kind":
                            poker_hand.setText("three of a kind");
                            break;
                        case "four of a kind":
                            poker_hand.setText("four of a kind");
                            break;
                        case "full house":
                            poker_hand.setText("full house");
                            break;
                    }

                    boolean straight_flush = false;

                    if (straight(cards_in_play).equals("straight") && flush(cards_in_play).equals("flush")) {
                        straight_flush = true;
                        poker_hand.setText("straight flush");
                    }

                    if (!straight_flush && straight(cards_in_play).equals("straight")) {
                        poker_hand.setText("straight");
                    }

                    if (straight_flush && Ace_high_card) {
                        poker_hand.setText("royal flush");
                    }


                    if (deal_count == 2) {
                        deal.setText("deal");
                        deal_count = 0;

                        switch (bet_count) {
                            case 1:

                                if (poker_hand.getText().toString().equals("pair")) {
                                    credits += 1;
                                    poker_hand.append(" winnings " + 1);
                                } else if (poker_hand.getText().toString().equals("two pair")) {
                                    credits += 2;
                                    poker_hand.append(" winnings " + 2);
                                } else if (poker_hand.getText().toString().equals("three of a kind")) {
                                    credits += 3;
                                    poker_hand.append(" winnings " + 3);
                                } else if (poker_hand.getText().toString().equals("straight")) {
                                    credits += 4;
                                    poker_hand.append(" winnings " + 4);
                                } else if (poker_hand.getText().toString().equals("flush")) {
                                    credits += 5;
                                    poker_hand.append(" winnings " + 5);
                                } else if (poker_hand.getText().toString().equals("full house")) {
                                    credits += 6;
                                    poker_hand.append(" winnings " + 6);
                                } else if (poker_hand.getText().toString().equals("4 of a kind")) {
                                    credits += 30;
                                    poker_hand.append(" winnings " + 30);
                                } else if (poker_hand.getText().toString().equals("straight flush")) {
                                    credits += 50;
                                    poker_hand.append(" winnings " + 50);
                                } else if (poker_hand.getText().toString().equals("royal flush")) {
                                    credits += 250;
                                    poker_hand.append(" winnings " + 250);
                                }

                                break;
                            case 2:
                                if (poker_hand.getText().toString().equals("pair")) {
                                    credits += 2;
                                    poker_hand.append(" winnings " + 2);
                                } else if (poker_hand.getText().toString().equals("two pair")) {
                                    credits += 4;
                                    poker_hand.append(" winnings " + 4);
                                } else if (poker_hand.getText().toString().equals("three of a kind")) {
                                    credits += 6;
                                    poker_hand.append(" winnings " + 6);
                                } else if (poker_hand.getText().toString().equals("straight")) {
                                    credits += 8;
                                    poker_hand.append(" winnings " + 8);
                                } else if (poker_hand.getText().toString().equals("flush")) {
                                    credits += 10;
                                    poker_hand.append(" winnings " + 10);
                                } else if (poker_hand.getText().toString().equals("full house")) {
                                    credits += 12;
                                    poker_hand.append(" winnings " + 12);
                                } else if (poker_hand.getText().toString().equals("4 of a kind")) {
                                    credits += 60;
                                    poker_hand.append(" winnings " + 60);
                                } else if (poker_hand.getText().toString().equals("straight flush")) {
                                    credits += 100;
                                    poker_hand.append(" winnings " + 100);
                                } else if (poker_hand.getText().toString().equals("royal flush")) {
                                    credits += 500;
                                    poker_hand.append(" winnings " + 500);
                                }
                                break;
                            case 3:
                                if (poker_hand.getText().toString().equals("pair")) {
                                    credits += 3;
                                    poker_hand.append(" winnings " + 3);
                                } else if (poker_hand.getText().toString().equals("two pair")) {
                                    credits += 6;
                                    poker_hand.append(" winnings " + 6);
                                } else if (poker_hand.getText().toString().equals("three of a kind")) {
                                    credits += 9;
                                    poker_hand.append(" winnings " + 9);
                                } else if (poker_hand.getText().toString().equals("straight")) {
                                    credits += 12;
                                    poker_hand.append(" winnings " + 12);
                                } else if (poker_hand.getText().toString().equals("flush")) {
                                    credits += 15;
                                    poker_hand.append(" winnings " + 15);
                                } else if (poker_hand.getText().toString().equals("full house")) {
                                    credits += 18;
                                    poker_hand.append(" winnings " + 18);
                                } else if (poker_hand.getText().toString().equals("4 of a kind")) {
                                    credits += 90;
                                    poker_hand.append(" winnings " + 90);
                                } else if (poker_hand.getText().toString().equals("straight flush")) {
                                    credits += 150;
                                    poker_hand.append(" winnings " + 150);
                                } else if (poker_hand.getText().toString().equals("royal flush")) {
                                    credits += 750;
                                    poker_hand.append(" winnings " + 750);
                                }
                                break;
                            case 4:
                                if (poker_hand.getText().toString().equals("pair")) {
                                    credits += 4;
                                    poker_hand.append(" winnings " + 4);
                                } else if (poker_hand.getText().toString().equals("two pair")) {
                                    credits += 8;
                                    poker_hand.append(" winnings " + 8);
                                } else if (poker_hand.getText().toString().equals("three of a kind")) {
                                    credits += 12;
                                    poker_hand.append(" winnings " + 12);
                                } else if (poker_hand.getText().toString().equals("straight")) {
                                    credits += 16;
                                    poker_hand.append(" winnings " + 16);
                                } else if (poker_hand.getText().toString().equals("flush")) {
                                    credits += 20;
                                    poker_hand.append(" winnings " + 20);
                                } else if (poker_hand.getText().toString().equals("full house")) {
                                    credits += 24;
                                    poker_hand.append(" winnings " + 24);
                                } else if (poker_hand.getText().toString().equals("4 of a kind")) {
                                    credits += 120;
                                    poker_hand.append(" winnings " + 120);
                                } else if (poker_hand.getText().toString().equals("straight flush")) {
                                    credits += 200;
                                    poker_hand.append(" winnings " + 200);
                                } else if (poker_hand.getText().toString().equals("royal flush")) {
                                    credits += 1000;
                                    poker_hand.append(" winnings " + 1000);
                                }
                                break;
                            case 5:
                                if (poker_hand.getText().toString().equals("pair")) {
                                    credits += 5;
                                    poker_hand.append(" winnings " + 5);
                                } else if (poker_hand.getText().toString().equals("two pair")) {
                                    credits += 10;
                                    poker_hand.append(" winnings " + 10);
                                } else if (poker_hand.getText().toString().equals("three of a kind")) {
                                    credits += 10;
                                    poker_hand.append(" winnings " + 10);
                                } else if (poker_hand.getText().toString().equals("straight")) {
                                    credits += 15;
                                    poker_hand.append(" winnings " + 15);
                                } else if (poker_hand.getText().toString().equals("flush")) {
                                    credits += 20;
                                    poker_hand.append(" winnings " + 20);
                                } else if (poker_hand.getText().toString().equals("full house")) {
                                    credits += 25;
                                    poker_hand.append(" winnings " + 25);
                                } else if (poker_hand.getText().toString().equals("4 of a kind")) {
                                    credits += 30;
                                    poker_hand.append(" winnings " + 30);
                                } else if (poker_hand.getText().toString().equals("straight flush")) {
                                    credits += 150;
                                    poker_hand.append(" winnings " + 150);
                                } else if (poker_hand.getText().toString().equals("royal flush")) {
                                    credits += 4000;
                                    poker_hand.append(" winnings " + 4000);
                                }
                                break;
                        }
                        credit_title.setText("credits: " + credits);
                        make_bet.setVisibility(View.VISIBLE);
                        bet_count = 0;
                    }
                }
                else{
                    Toast.makeText(getActivity(),"please make a bet",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

      public String kinds_and_pairs_and_fullhouse(String[] cards) {
               HashMap<String,Integer> count = new HashMap<String,Integer>();
               boolean pair = false;
               boolean two_pair = false;
               boolean three_of_a_kind = false;
               boolean four_of_a_kind = false;
               boolean full_house = false;

               for(int i = 0; i < cards.length; i++) {
                   for (int j = 0; j < card_class.numbers.length; j++) {
                       if (cards[i].contains(card_class.numbers[j])) {
                           if (count.get(card_class.numbers[j]) != null) {
                               count.put(card_class.numbers[j], count.get(card_class.numbers[j]) + 1);
                           } else if (count.get(card_class.numbers[j]) == null) {
                               count.put(card_class.numbers[j], 1);
                           }
                       }
                   }
               }

               int pair_count = 0;

               for(String key : count.keySet()){
                   if(count.get(key) == 2){
                       pair = true;
                       pair_count++;
                       if(pair_count == 2){
                           two_pair = true;
                       }
                   }
                   else if(count.get(key) == 3){
                       three_of_a_kind = true;
                   }
                   else if(count.get(key) == 4){
                       four_of_a_kind = true;
                   }
               }

               if(pair && three_of_a_kind)
                   full_house = true;

               String win = " ";

               if(!two_pair && !full_house && pair){
                   win = "pair";
               }
               else if(two_pair){
                    win = "two pair";
               }
               else if(!full_house && three_of_a_kind){
                    win = "three of a kind";
               }
               else if(four_of_a_kind){
                   win = "four of a kind";
               }
               else if(full_house){
                   win = "full house";
               }

               return win;
      }

      public String flush(String[] cards){

          HashMap<String,Integer> count = new HashMap<String,Integer>();

          for(int i = 0; i<cards.length; i++){
              for(int j = 0; j<card_class.card_type.length; j++) {
                  if (cards[i].contains(card_class.card_type[j])) {
                      if (count.get(card_class.card_type[j]) == null) {
                          count.put(card_class.card_type[j], 1);
                      } else if (count.get(card_class.card_type[j]) != null) {
                          count.put(card_class.card_type[j], count.get(card_class.card_type[j]) + 1);
                      }
                  }
              }
          }

          String win = " ";

          for(String k : count.keySet()){
                if(count.get(k) == 5)
                  win = "flush";
          }
        return win;
      }

      public String straight(String[] cards){

           ArrayList<Integer> card_list = new ArrayList<Integer>();

           boolean straight = false;

           for(int i = 0; i<cards.length;i++){
                if(cards[i].contains("one")) {
                       card_list.add(14);
                }
                else if (cards[i].contains("two")){
                       card_list.add(2);
               }
               else if (cards[i].contains("three")){
                    card_list.add(3);
                }
               else if(cards[i].contains("four")){
                    card_list.add(4);
                }
                else if(cards[i].contains("five")){
                    card_list.add(5);
                }
                else if(cards[i].contains("six")){
                    card_list.add(6);
                }
                else if(cards[i].contains("seven")){
                    card_list.add(7);
                }
                else if(cards[i].contains("eight")){
                    card_list.add(8);
                }
                else if(cards[i].contains("nine")){
                    card_list.add(9);
                }
                else if(cards[i].contains("ten")){
                    card_list.add(10);
                }
                else if(cards[i].contains("eleven")){
                    card_list.add(11);
                }
                else if(cards[i].contains("twelve")){
                    card_list.add(12);
                }
                else if(cards[i].contains("thirtteen")){
                    card_list.add(13);
                }
           }

          Collections.sort(card_list);

          if(card_list.get(4) == 14){
              Ace_high_card = true;
              straight = (card_list.get(0) == 2 && card_list.get(1) == 3 && card_list.get(2) == 4
                      && card_list.get(3) == 5) || (card_list.get(0) == 10 && card_list.get(1) == 11
                      && card_list.get(2) == 12 && card_list.get(3) == 13);
          }
          else{
              Ace_high_card = false;
              int test = card_list.get(0) + 1;

              for(int i = 1; i < 5; i++){
                  if(card_list.get(i) != test)
                      return " ";

                  test++;
              }

              straight = true;
          }

          String win = " ";

          if(straight)
            win = "straight";

          return win;
      }

}
