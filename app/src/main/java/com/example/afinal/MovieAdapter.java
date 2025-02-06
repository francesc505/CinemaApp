package com.example.afinal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private List<Movie> movieList;

    itemClickListener itemClickListener;
    public MovieAdapter(Context context , List<Movie> movies,itemClickListener itemClickListener){ //qua
        this.context = context;
        movieList = movies;
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new MovieHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        Movie movie = movieList.get(position);
        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getTitle());
        holder.made.setText(movie.getMade());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView title ,made , rating;
        TextView latitudine, longitudine;
        ConstraintLayout constraintLayout;
        itemClickListener itemClickListener;
        Button button;
        public  MovieHolder(@NonNull View itemView, itemClickListener itemClickListener) {//qua
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title_tv);
            made= itemView.findViewById(R.id.made);
            rating = itemView.findViewById(R.id.rating);
            this.itemClickListener = itemClickListener;
            button = itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
            constraintLayout = itemView.findViewById(R.id.second_layout);
        }

        @Override
        public void onClick(View v) {//metodo
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface itemClickListener{
        void onItemClick(int position);
    }
}