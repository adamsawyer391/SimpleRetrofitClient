package com.cosmic.retrofitsample.rvhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cosmic.retrofitsample.R;
import com.cosmic.retrofitsample.model.Change;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.GitHubViewHolder> {

    private final List<Change> changes;
    private final Context context;

    public RecyclerAdapter(List<Change> changes, Context context) {
        this.changes = changes;
        this.context = context;
    }

    @NonNull
    @Override
    public GitHubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_item, parent, false);
        return new GitHubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubViewHolder holder, int position) {
        holder.tvGit.setText(changes.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        if (changes!= null){
            return changes.size();
        }else{
            return 0;
        }
    }

    static class GitHubViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvGit;

        public GitHubViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGit = itemView.findViewById(R.id.tvGit);
        }
    }
}
