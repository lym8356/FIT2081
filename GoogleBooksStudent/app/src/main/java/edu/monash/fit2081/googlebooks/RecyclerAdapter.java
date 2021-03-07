package edu.monash.fit2081.googlebooks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<GoogleBook> data = new ArrayList<GoogleBook>();
    public void setData (ArrayList<GoogleBook> data){this.data = data;}

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.titleTv.setText(data.get(position).getBookTitle());
        holder.authorTv.setText(data.get(position).getAuthors());
        holder.publishedYrTV.setText(data.get(position).getPublishedDate().substring(0,4));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTv;
        public TextView authorTv;
        public TextView publishedYrTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.book_title);
            authorTv = itemView.findViewById(R.id.book_author);
            publishedYrTV = itemView.findViewById(R.id.published_year);
        }
    }
}
