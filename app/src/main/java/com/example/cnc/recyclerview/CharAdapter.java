package com.example.cnc.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnc.CharListActivity;
import com.example.cnc.Intents;
import com.example.cnc.db.PlayerChar;
import com.example.cnc.R;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.13
 * A class to adapt a PlayerChar RecyclerView. Excessive hackery within.
 */

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.CharViewHolder> {
  private List<PlayerChar> data;

  public static class CharViewHolder extends RecyclerView.ViewHolder {
    TextView line1;
    TextView line2;
    CharListActivity activity;

    public CharViewHolder(@NonNull View view, List<PlayerChar> data) {
      super(view);
//      this.activity = (CharListActivity)parent.getParent();
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          System.out.println("adapter position = " + String.valueOf(getAdapterPosition()));
          view.getContext().startActivity(
              Intents.charSheet(view.getContext(), data.get(getAdapterPosition()).getCharId()));
//          activity.selectChar(getAdapterPosition());
        }
      });
      line1 = view.findViewById(R.id.itemLine1);
      line2 = view.findViewById(R.id.itemLine2);
    }
  }

  public CharAdapter(List<PlayerChar> data) {
    this.data = data;
  }

  @NonNull
  @Override
  public CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new CharViewHolder(view, data);
  }

  @Override
  public void onBindViewHolder(@NonNull CharViewHolder holder, int position) {
    holder.line1.setText(data.get(position).getName());
    holder.line2.setText(String.valueOf(data.get(position).getCharId()));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }
}
