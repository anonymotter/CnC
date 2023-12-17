package com.example.cnc.dataview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnc.Intents;
import com.example.cnc.R;
import com.example.cnc.Statics;
import com.example.cnc.db.PlayerChar;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.13
 * A class to adapt a PlayerChar RecyclerView for a list of characters. A long click will remove
 * a character from the campaign.
 */

public class CampaignCharListAdapter
    extends RecyclerView.Adapter<CampaignCharListAdapter.CharViewHolder> {
  private List<PlayerChar> data;

  public static class CharViewHolder extends RecyclerView.ViewHolder {

    TextView line1;
    TextView line2;

    public CharViewHolder(@NonNull View view, List<PlayerChar> data) {
      super(view);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          view.getContext().startActivity(
              Intents.charSheet(view.getContext(), data.get(getAdapterPosition()).getCharId()));
        }
      });

      view.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          PlayerChar removedChar = data.get(getAdapterPosition());
          removedChar.setCampaignId(1);
          Statics.getDao().update(removedChar);
          return true;
        }
      });
      line1 = view.findViewById(R.id.itemLine1);
      line2 = view.findViewById(R.id.itemLine2);
    }
  }

  public CampaignCharListAdapter(List<PlayerChar> data) {
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
    holder.line2.setText(data.get(position).describeWithOwner());
  }

  @Override
  public int getItemCount() {
    return data.size();
  }
}
