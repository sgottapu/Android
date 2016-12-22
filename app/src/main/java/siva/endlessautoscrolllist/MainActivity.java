package siva.endlessautoscrolllist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mAutoCircularRecyclerView;
    ListAdapter adapter;
    AutoScrollHelper mAutoScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutoCircularRecyclerView = (RecyclerView) findViewById(R.id.rv_demo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAutoScroll = new AutoScrollHelper();
        mAutoScroll.startScroll(mAutoCircularRecyclerView, adapter.getItemCount(), 3);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAutoScroll.stopScrolling();

    }
}
