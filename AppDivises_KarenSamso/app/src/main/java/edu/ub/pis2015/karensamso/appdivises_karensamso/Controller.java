package edu.ub.pis2015.karensamso.appdivises_karensamso;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Karen on 04/06/2015.
 */
public class Controller extends Application {

    private ArrayList<PreviousConversion> conversionList;

    public Controller(){
        conversionList = new ArrayList<PreviousConversion>();
    }

    public void save(Context context){

        String fileP = "previousConversionsSaved";
        FileOutputStream fosP;
        ObjectOutputStream osP;

        try {
            fosP = context.openFileOutput(fileP, Context.MODE_PRIVATE);
            try {
                osP = new ObjectOutputStream(fosP);
                osP.writeObject(conversionList);
                osP.close();
                fosP.close();
                Log.i("llista guardada mida", String.valueOf(conversionList.size()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load(Context context) {

        String fileP = "previousConversionsSaved";
        FileInputStream fisP;
        ObjectInputStream isP;

        try {
            fisP = context.openFileInput(fileP);
            try {
                isP = new ObjectInputStream(fisP);
                try {
                    ArrayList<PreviousConversion> loadedP = (ArrayList<PreviousConversion>) isP.readObject(); //I read the missions data
                    isP.close();
                    fisP.close();
                    conversionList = new ArrayList<PreviousConversion>(loadedP); //I save the read missions data
                    Log.i("LLISTA CARREGADA MIDA", String.valueOf(conversionList.size()));

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addConversion(PreviousConversion conv) {
        conversionList.add(conv);
    }

    public ArrayList<PreviousConversion> getConversionList(){
        return conversionList;
    }
}
