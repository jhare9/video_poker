package edu.computing.of.school.usm.jon.videopoker;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class video_poker_main extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_poker_main);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.container,new PlaceHolder()).commit();
    }


   public static class PlaceHolder extends Fragment {

       @Override
       public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.poker_menu,container,false);


           Button video_poker = (Button) view.findViewById(R.id.play_video_poker);

           video_poker.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    getFragmentManager().beginTransaction().replace(R.id.container,new Game_Fragment()).addToBackStack("video_poker").commit();
               }
           });

           return view;
       }
   }



}
