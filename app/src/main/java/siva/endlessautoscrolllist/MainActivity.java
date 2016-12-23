package siva.endlessautoscrolllist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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
        adapter = new ListAdapter(getListItems());
        mAutoCircularRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAutoCircularRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mAutoCircularRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        mAutoCircularRecyclerView.setAdapter(adapter);
        mAutoScroll.startScroll(mAutoCircularRecyclerView, adapter.getItemCount(), 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAutoScroll.stopScrolling();

    }

    private List<String> getListItems(){
        List<String> stringList = new ArrayList<>();
        stringList.add("Item 1");
        stringList.add("Item 2");
        stringList.add("Item 3");
        stringList.add("Item 4");
        stringList.add("Item 5");
        stringList.add("Item 6");
        stringList.add("Item 7");
        stringList.add("Item 8");
        stringList.add("Item 9");
        stringList.add("Item 10");

        return stringList;
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
