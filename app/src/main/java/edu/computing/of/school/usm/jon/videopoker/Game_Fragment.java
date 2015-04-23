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
    private TextView held_1;
    private TextView held_2;
    private TextView held_3;
    private TextView held_4;
    private TextView held_5;
    private Card_Class card_class;
    private ImageButton card_1;
    private ImageButton card_2;
    private ImageButton card_3;
    private ImageButton card_4;
    private ImageButton card_5;
    private ImageButton cpuCard_1;
    private ImageButton cpuCard_2;
    private ImageButton cpuCard_3;
    private ImageButton cpuCard_4;
    private ImageButton cpuCard_5;
    private TextView poker_hand;
    private Button deal;
    private Button bet;
    private Button bet_max;
    private Button credit_title;
    private TextView make_bet;
    private Stack<String> cards;
    private String[] player_cards_in_play;
    private String[] cpu_cards_in_play;
    private int deal_count = 0;
    private int bet_count = 0;
    private boolean Ace_high_card = false;
    private int credits;
    private HashMap<String,Integer> comparison;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_poker_game,container,false);

        // initialize players cards
        card_1 = (ImageButton) view.findViewById(R.id.card_1);
        card_2 = (ImageButton) view.findViewById(R.id.card_2);
        card_3 = (ImageButton) view.findViewById(R.id.card_3);
        card_4 = (ImageButton) view.findViewById(R.id.card_4);
        card_5 = (ImageButton) view.findViewById(R.id.card_5);

        // initialize computer cards
        cpuCard_1 =(ImageButton)view.findViewById(R.id.comp_card_1);
        cpuCard_2 = (ImageButton)view.findViewById(R.id.comp_card_2);
        cpuCard_3 = (ImageButton)view.findViewById(R.id.comp_card_3);
        cpuCard_4 = (ImageButton)view.findViewById(R.id.comp_card_4);
        cpuCard_5 = (ImageButton)view.findViewById(R.id.comp_card_5);

        // set the computer cards to invisible on the start of the game
        cpuCard_1.setVisibility(View.INVISIBLE);
        cpuCard_2.setVisibility(View.INVISIBLE);
        cpuCard_3.setVisibility((View.INVISIBLE));
        cpuCard_4.setVisibility(View.INVISIBLE);
        cpuCard_5.setVisibility(View.INVISIBLE);

        //initialize the held text views.
        held_1 = (TextView)view.findViewById(R.id.held_1);
        held_2 = (TextView)view.findViewById(R.id.held_2);
        held_3 = (TextView)view.findViewById(R.id.held_3);
        held_4 = (TextView)view.findViewById(R.id.held_4);
        held_5 = (TextView)view.findViewById(R.id.held_5);

        //set the held text views to invisible on start
        held_1.setVisibility(View.INVISIBLE);
        held_2.setVisibility(View.INVISIBLE);
        held_3.setVisibility(View.INVISIBLE);
        held_4.setVisibility(View.INVISIBLE);
        held_5.setVisibility(View.INVISIBLE);

        /* set the bet max bet , menu button , credits, deal and the winner title to the appropriate layout element */
        bet = (Button) view.findViewById(R.id.bet);
        bet_max = (Button)view.findViewById(R.id.maxBet);
        // players starting credits
        credits = 100;
        credit_title = (Button)view.findViewById(R.id.credits);
        credit_title.setText("Credits: "+credits);
        poker_hand = (TextView)view.findViewById(R.id.pokerHand);
        deal = (Button) view.findViewById(R.id.deal);

        // initializes the players and computer cards
        card_class = new Card_Class();
        cards = new Stack<String>();
        cards = card_class.new_game();
        player_cards_in_play = new String[5];
        cpu_cards_in_play = new String[5];

        //set a point system to compare between the computers hand and the players hand.
        comparison = new HashMap<String,Integer>();
        comparison.put("nothing",0);
        comparison.put("pair",1);
        comparison.put("two pair",2);
        comparison.put("three of a kind", 3);
        comparison.put("straight",4);
        comparison.put("flush",5);
        comparison.put("full house",6);
        comparison.put("four of a kind", 7);
        comparison.put("straight flush",8);
        comparison.put("royal flush",9);

          // card image button click listeners  used to hold a card
        card_1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                if(deal_count > 0) {
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
                if(deal_count > 0) {
                    if (held_5.getVisibility() == View.INVISIBLE)
                        held_5.setVisibility(View.VISIBLE);
                    else
                        held_5.setVisibility(View.INVISIBLE);
                }
            }
        });
        // end of onclick listeners for the cards

        // create the go back to the main menu on the fly
        Button menu = (Button) view.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container,new video_poker_main.PlaceHolder()).commit();
            }
        });// end of menu onClick listener

        // set up the bet onclick listener
        bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bet_count++;

                if(bet_count > 5)
                    bet_count = 0;

                poker_hand.setText("Bet = "+bet_count);

            }
        }); // end of bet onclick listener.

        // set up the max bet onclick listener
        bet_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set the bet count to 5
                bet_count = 5;
                poker_hand.setText("Bet = "+bet_count);
            }
        });// end of max bet onclick listener

        // set up the deal onclick listener also contains the game logic
        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // makes sure a bet has been placed before doing any game logic.
                if(bet_count == 0){
                    Toast.makeText(getActivity(),"please make a bet",Toast.LENGTH_SHORT).show();
                    return;
                }

                // makes sure the player can make bet to keep the game going
                if(credits <= 0)
                    credits = 20;

                // increment the deal count
                deal_count++;

                // computer only gets dealt one set of cards in my game
                if(deal_count <= 1) {

                    for (int i = 0; i < 5; i++) {
                        if(cards.empty())
                            cards = card_class.new_game();

                        cpu_cards_in_play[i] = cards.pop();
                    }
                }

                // time to take the player's money
                if(deal_count < 2)
                credits -= bet_count;
                // set the poker hand to nothing
                poker_hand.setText("nothing");

                // let the user hold certain cards and show the user his cards
                for(int i = 0; i < 5; i++) {
                    if(cards.empty())
                        cards = card_class.new_game();

                    switch (i) {
                        case 0:
                            if (held_1.getVisibility() == View.INVISIBLE) {
                                player_cards_in_play[i] = cards.pop();
                                card_1.setBackgroundResource(getResources().getIdentifier(player_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                            }
                            held_1.setVisibility(View.INVISIBLE);
                            break;
                        case 1:
                            if (held_2.getVisibility() == View.INVISIBLE) {
                                player_cards_in_play[i] = cards.pop();
                                card_2.setBackgroundResource(getResources().getIdentifier(player_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                            }
                            held_2.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            if (held_3.getVisibility() == View.INVISIBLE) {
                                player_cards_in_play[i] = cards.pop();
                                card_3.setBackgroundResource(getResources().getIdentifier(player_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                            }
                            held_3.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            if (held_4.getVisibility() == View.INVISIBLE) {
                                player_cards_in_play[i] = cards.pop();
                                card_4.setBackgroundResource(getResources().getIdentifier(player_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                            }
                            held_4.setVisibility(View.INVISIBLE);
                            break;
                        case 4:
                            if (held_5.getVisibility() == View.INVISIBLE) {
                                player_cards_in_play[i] = cards.pop();
                                card_5.setBackgroundResource(getResources().getIdentifier(player_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                            }
                            held_5.setVisibility(View.INVISIBLE);
                            break;
                    }
                }


                // tell the user what kind of hand he or she has
                // detects if there is a pair, two pair, three of a kind ,four of a kind or full house.
                switch (kinds_and_pairs_and_fullhouse(player_cards_in_play)) {
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

                boolean straight_flush = false; // sets the straight flush to false use in detecting a royal flush
                // detects if there is straight straight flush or royal flush,
                if (straight(player_cards_in_play).equals("straight") && flush(player_cards_in_play).equals("flush")) {
                    straight_flush = true;
                    poker_hand.setText("straight flush");
                }

                if (!straight_flush && straight(player_cards_in_play).equals("straight")) {
                    poker_hand.setText("straight");
                }

                if(!straight_flush && flush(player_cards_in_play).equals("flush")) {
                    poker_hand.setText("flush");
                }

                if (straight_flush && Ace_high_card) {
                    poker_hand.setText("royal flush");
                }


                // if statement to start the winning calculations
                if(deal_count == 2){
                    // reset deal count
                    deal_count = 0;

                    // show the computers cards
                    for(int i = 0; i< 5; i++)
                        switch(i){
                            case 0:
                                cpuCard_1.setBackgroundResource(getResources().getIdentifier(cpu_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                                cpuCard_1.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                cpuCard_2.setBackgroundResource(getResources().getIdentifier(cpu_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                                cpuCard_2.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                cpuCard_3.setBackgroundResource(getResources().getIdentifier(cpu_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                                cpuCard_3.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                cpuCard_4.setBackgroundResource(getResources().getIdentifier(cpu_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                                cpuCard_4.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                cpuCard_5.setBackgroundResource(getResources().getIdentifier(cpu_cards_in_play[i], "drawable", getActivity().getApplicationInfo().packageName));
                                cpuCard_5.setVisibility(View.VISIBLE);
                                break;
                        }

                     // test if the computer won or not

                    String computers_hand = "nothing";

                    switch (kinds_and_pairs_and_fullhouse(cpu_cards_in_play)) {
                        case "pair":
                            computers_hand = "pair";
                            break;
                        case "two pair":
                            computers_hand = "two pair";
                            break;
                        case "three of a kind":
                            computers_hand = "three of a kind";
                            break;
                        case "four of a kind":
                            computers_hand = "four of a kind";
                            break;
                        case "full house":
                            computers_hand = "full house";
                            break;
                    }

                    boolean straight_flush2 = false; // sets the straight flush to false use in detecting a royal flush
                    // detects if there is straight straight flush or royal flush,
                    if (straight(cpu_cards_in_play).equals("straight") && flush(cpu_cards_in_play).equals("flush")) {
                        straight_flush2 = true;
                        computers_hand = "straight flush";
                    }

                    if (!straight_flush && straight(cpu_cards_in_play).equals("straight")) {
                        computers_hand = "straight";
                    }

                    if(!straight_flush && flush(cpu_cards_in_play).equals("flush")) {
                        computers_hand = "flush";
                    }

                    if (straight_flush && Ace_high_card) {
                        computers_hand = "royal flush";
                    }



                    if(comparison.get(computers_hand) > comparison.get(poker_hand.getText().toString())){
                        poker_hand.setText("computer wins!!! "+computers_hand);
                        credit_title.setText("Credits: "+credits);
                        bet_count = 0;
                        return;
                    }
                    else if(comparison.get(computers_hand) == comparison.get(poker_hand.getText().toString())){
                        poker_hand.setText("Tied game: computers hand is "+computers_hand+" and the players hand is "+poker_hand.getText().toString());
                        credits += bet_count;
                        credit_title.setText("Credits: "+credits);
                        bet_count = 0;
                        return;
                    }// end of the comparsions between the computer and the player

                    // switch to decide the payout and a switch to decided which hand to pay out on.
                    switch (bet_count) {
                        case 1:
                            switch(poker_hand.getText().toString()){
                                case "pair":
                                    credits += 1;
                                    poker_hand.append(" winnings " + 1);
                                    break;
                                case "two pair":
                                    credits += 2;
                                    poker_hand.append(" winnings " + 2);
                                    break;
                                case "three of a kind":
                                    credits += 3;
                                    poker_hand.append(" winnings " + 3);
                                    break;
                                case "straight":
                                    credits += 4;
                                    poker_hand.append(" winnings " + 4);
                                    break;
                                case "flush":
                                    credits += 5;
                                    poker_hand.append(" winnings " + 5);
                                    break;
                                case "full house":
                                    credits += 6;
                                    poker_hand.append(" winnings " + 6);
                                    break;
                                case "four of a kind":
                                    credits += 30;
                                    poker_hand.append(" winnings " + 30);
                                    break;
                                case "straight flush":
                                    credits += 50;
                                    poker_hand.append(" winnings " + 50);
                                    break;
                                case "royal flush":
                                    credits += 250;
                                    poker_hand.append(" winnings " + 250);
                                    break;
                            }
                            break;
                        case 2:
                            switch(poker_hand.getText().toString()){
                                case "pair":
                                    credits += 2;
                                    poker_hand.append(" winnings " + 2);
                                    break;
                                case "two pair":
                                    credits += 4;
                                    poker_hand.append(" winnings " + 4);
                                    break;
                                case "three of a kind":
                                    credits += 6;
                                    poker_hand.append(" winnings " + 6);
                                    break;
                                case "straight":
                                    credits += 8;
                                    poker_hand.append(" winnings " + 8);
                                    break;
                                case "flush":
                                    credits += 10;
                                    poker_hand.append(" winnings " + 10);
                                    break;
                                case "full house":
                                    credits += 12;
                                    poker_hand.append(" winnings " + 12);
                                    break;
                                case "four of a kind":
                                    credits += 60;
                                    poker_hand.append(" winnings " + 60);
                                    break;
                                case "straight flush":
                                    credits += 100;
                                    poker_hand.append(" winnings " + 100);
                                    break;
                                case "royal flush":
                                    credits += 500;
                                    poker_hand.append(" winnings " + 500);
                                    break;
                            }
                            break;
                        case 3:
                            switch(poker_hand.getText().toString()){
                                case "pair":
                                    credits += 3;
                                    poker_hand.append(" winnings " + 3);
                                    break;
                                case "two pair":
                                    credits += 6;
                                    poker_hand.append(" winnings " + 6);
                                    break;
                                case "three of a kind":
                                    credits += 9;
                                    poker_hand.append(" winnings " + 9);
                                    break;
                                case "straight":
                                    credits += 12;
                                    poker_hand.append(" winnings " + 12);
                                    break;
                                case "flush":
                                    credits += 15;
                                    poker_hand.append(" winnings " + 15);
                                    break;
                                case "full house":
                                    credits += 18;
                                    poker_hand.append(" winnings " + 18);
                                    break;
                                case "four of a kind":
                                    credits += 90;
                                    poker_hand.append(" winnings " + 90);
                                    break;
                                case "straight flush":
                                    credits += 150;
                                    poker_hand.append(" winnings " + 150);
                                    break;
                                case "royal flush":
                                    credits += 750;
                                    poker_hand.append(" winnings " + 750);
                                    break;
                            }
                            break;
                        case 4:
                            switch(poker_hand.getText().toString()) {
                                case "pair":
                                    credits += 4;
                                    poker_hand.append(" winnings " + 4);
                                    break;
                                case "two pair":
                                    credits += 8;
                                    poker_hand.append(" winnings " + 8);
                                    break;
                                case "three of a kind":
                                    credits += 12;
                                    poker_hand.append(" winnings " + 12);
                                    break;
                                case "straight":
                                    credits += 16;
                                    poker_hand.append(" winnings " + 16);
                                    break;
                                case "flush":
                                    credits += 20;
                                    poker_hand.append(" winnings " + 20);
                                    break;
                                case "full house":
                                    credits += 24;
                                    poker_hand.append(" winnings " + 24);
                                    break;
                                case "four of a kind":
                                    credits += 120;
                                    poker_hand.append(" winnings " + 120);
                                    break;
                                case "straight flush":
                                    credits += 200;
                                    poker_hand.append(" winnings " + 200);
                                    break;
                                case "royal flush":
                                    credits += 1000;
                                    poker_hand.append(" winnings " + 1000);
                                    break;
                            }
                            break;
                        case 5:
                            switch(poker_hand.getText().toString()) {
                                case "pair":
                                    credits += 5;
                                    poker_hand.append(" winnings " + 5);
                                    break;
                                case "two pair":
                                    credits += 10;
                                    poker_hand.append(" winnings " + 10);
                                    break;
                                case "three of a kind":
                                    credits += 10;
                                    poker_hand.append(" winnings " + 10);
                                    break;
                                case "straight":
                                    credits += 15;
                                    poker_hand.append(" winnings " + 15);
                                    break;
                                case "flush":
                                    credits += 20;
                                    poker_hand.append(" winnings " + 20);
                                    break;
                                case "full house":
                                    credits += 25;
                                    poker_hand.append(" winnings " + 25);
                                    break;
                                case "four of a kind":
                                    credits += 30;
                                    poker_hand.append(" winnings " + 30);
                                    break;
                                case "straight flush":
                                    credits += 150;
                                    poker_hand.append(" winnings " + 150);
                                    break;
                                case "royal flush":
                                    credits += 4000;
                                    poker_hand.append(" winnings " + 4000);
                                    break;
                            }
                            break;
                    }
                    // set the credits to the right amount
                    credit_title.setText("credits: " + credits);
                    // set bet count back to zero
                    bet_count = 0;
                }


            }
        });// end of the deal onclick listener.


        return view;
    }
      // function to calculate three of a kind, four of a kind, pair, two pairs, and full houses
      // takes in a string of cards
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


     // decides if there is a flush or not
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
      // decides if there is a straight or not
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
