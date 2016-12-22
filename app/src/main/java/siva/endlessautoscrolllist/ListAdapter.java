package siva.endlessautoscrolllist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sivaprasadg on 22/12/16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListVh>{

    //  defined constant for infinity scrolling
    private final int MAX_COUNT = 1000000;
    private int cycle = 0;
    private int listItemsSize;
    private List<String> photoUrls;

    public ListAdapter(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        listItemsSize = photoUrls.size();
    }

    @Override
    public ListVh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_item, parent, false);
        return new ListVh(view);
    }

    @Override
    public void onBindViewHolder(ListVh holder, int position) {
//        Picasso.with(holder.mImageView.getContext()).load(photoUrls.get(getCyclePosition(position))).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return MAX_COUNT;
    }


    class ListVh extends RecyclerView.ViewHolder{

        public ListVh(View itemView) {
            super(itemView);
        }
    }
    private int getCyclePosition(int position){
        cycle = position / listItemsSize;
        if ((cycle > 0)){
            return position - (cycle * listItemsSize);

        }else {
            return position;
        }
    }

}
