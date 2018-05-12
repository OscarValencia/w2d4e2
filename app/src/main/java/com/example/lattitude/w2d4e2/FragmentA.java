package com.example.lattitude.w2d4e2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lattitude on 5/11/2018.
 */

public class FragmentA extends Fragment implements View.OnClickListener{
    ElementClicked elementClicked;
    //private EditText inputMessage;
    private Button sendBt;

    public FragmentA(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btSend:
                new AsyncTaskCounter().execute(2000);
                //elementClicked.startThread("thread will start here");
                break;
        }
    }

    public interface ElementClicked{
        void startThread(String flag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            elementClicked = (ElementClicked)getActivity();
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+
                    " must implement 'ElementClicked' interface of 'FragmentA' object");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //inputMessage = view.findViewById(R.id.etMessage);
        sendBt = view.findViewById(R.id.btSend);
        sendBt.setOnClickListener(this);
    }

    public class AsyncTaskCounter extends AsyncTask<Integer,String,Long> {

        protected Long doInBackground(Integer... targets) {

            int count = targets.length;

            for (int i = 0; i < count; i++) {
                for (int j = 0; j <=targets[i] ; j++) {
                    try {
                        publishProgress(Integer.toString(j));
                        Thread.sleep(10);
                    }catch(InterruptedException s){
                        Log.d("ASYNC_TAG","Interrupted exception");
                    }
                }
                if (isCancelled()) break;
            }
            return new Long(0);
        }

        protected void onProgressUpdate(String... currentNumber) {
            elementClicked.startThread(currentNumber[0]);
        }

        //protected void onPostExecute(Long result) { }
    }
}
