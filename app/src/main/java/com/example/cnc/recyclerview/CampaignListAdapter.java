package com.example.cnc.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cnc.CampaignListActivity;
import com.example.cnc.Intents;
import com.example.cnc.R;
import com.example.cnc.Statics;
import com.example.cnc.db.Campaign;
import com.example.cnc.db.PlayerChar;

import java.util.List;

/**
 * @author Kyle Stefun
 * @since 2023.12.13
 * A class to adapt a Campaign RecyclerView for a list of campaigns.
 */

public class CampaignListAdapter
    extends RecyclerView.Adapter<CampaignListAdapter.CampaignViewHolder> {
  private List<Campaign> data;

  public static class CampaignViewHolder extends RecyclerView.ViewHolder {
    TextView line1;
    TextView line2;

    public CampaignViewHolder(@NonNull View view, List<Campaign> data) {
      super(view);
//      this.activity = (CharListActivity)parent.getParent();
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          view.getContext().startActivity(
              Intents.charSheet(view.getContext(), data.get(getAdapterPosition()).getCampaignId()));
        }
      });

      view.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          Statics.getDao().delete(data.get(getAdapterPosition()));
          return true;
        }
      });
      line1 = view.findViewById(R.id.itemLine1);
      line2 = view.findViewById(R.id.itemLine2);
    }
  }

  public CampaignListAdapter(List<Campaign> data) {
    this.data = data;
  }

  @NonNull
  @Override
  public CampaignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new CampaignViewHolder(view, data);
  }

  @Override
  public void onBindViewHolder(@NonNull CampaignViewHolder holder, int position) {
    holder.line1.setText(data.get(position).getName());
    holder.line2.setText(data.get(position).getDescription());
  }

  @Override
  public int getItemCount() {
    return data.size();
  }
}
