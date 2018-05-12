package com.example.lattitude.w2d4e2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentA.ElementClicked{
    FragmentB fragmentb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerA,new FragmentA())
                .commit();

        fragmentb = new FragmentB();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerB,fragmentb)
                .commit();

    }

    @Override
    //public void sendText(String text) {
    public void startThread(String flag){
        fragmentb.sendMessageToFragmentB(flag);
    }

}
