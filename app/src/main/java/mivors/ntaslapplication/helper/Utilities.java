package mivors.ntaslapplication.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import mivors.ntaslapplication.R;

/**
 * Created by Ahmed shaban on 11/30/2017.
 */

public class Utilities {
    public static int randInt(int min, int max) {

        Random rand =  new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

        //   return internet status
    public static boolean checkConnection(Context mContext) {
            if (ConnectivityReceiver.isConnected()) {
               return  true;
            } else {
                return false;
            }

    }

}
