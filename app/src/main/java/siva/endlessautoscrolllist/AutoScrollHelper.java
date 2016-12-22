package siva.endlessautoscrolllist;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by sivaprasadg on 22/12/16.
 */

public class AutoScrollHelper {
    public float MILLISECONDS_PER_INCH = 5760f;
    private final int DEFAULT_SCROLL_ITEMS = 1;
    static int count = 0;
    Handler handler;
    Runnable runnable;

    public float calculateSpeedPerPixel(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        MILLISECONDS_PER_INCH = MILLISECONDS_PER_INCH * displayMetrics.density ;

//        int distance = displayMetrics.widthPixels / 3;
////        distance = (int) (distance / displayMetrics.density);
////        MILLISECONDS_PER_INCH = MILLISECONDS_PER_INCH * (distance / 120);
//        final int speedPerPixel = (int) ((distance) / MILLISECONDS_PER_INCH);
//        Log.i("speedPerPixel", speedPerPixel + "");

        return MILLISECONDS_PER_INCH /displayMetrics.densityDpi;
//        return distance /MILLISECONDS_PER_INCH;
    }

    // 360 -- 480 dpi -- 3 , 160 no -- 240 -- 1.5 , 160 160 1
    public static int getDensity(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        Log.i("width", displayMetrics.widthPixels /3+" density : "+displayMetrics.densityDpi+"  "+displayMetrics.density);
        return displayMetrics.densityDpi;
    }

    public void startScroll(final RecyclerView recyclerView, final int totalItems, final int scrollItems) {
        final int speedScroll = (int) calculateSpeedPerPixel(recyclerView.getContext());
//        DisplayMetrics displayMetrics = recyclerView.getResources().getDisplayMetrics();
//        int distance = displayMetrics.widthPixels / 3;
//        final int time = distance / speedScroll;
//        Log.i("speedScroll", " h : " + displayMetrics.heightPixels + " w : " + displayMetrics.widthPixels);
//        Log.i("speedScroll", speedScroll + "");
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                if (count < totalItems) {
                    count = count + scrollItems;
                    recyclerView.smoothScrollToPosition(count);
                    handler.postDelayed(this, speedScroll);
                }
            }
        };
        handler.post(runnable);

    }

    public void stopScrolling(){
        if(handler != null){
            handler.removeCallbacks(runnable);
        }
    }
}
