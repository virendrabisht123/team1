package team.ixigo.hack.com.team1.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import team.ixigo.hack.com.team1.R;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;
import team.ixigo.hack.com.team1.utility.AppUtil;

public class HomeRecommendedPlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int TYPE_FLIGHT = 1;
    public static final int TYPE_BUDGET_FLIGHT = 2;

    public static final int TYPE_ITEM = 1;
    public static final int TYPE_PROGRESS = 2;
    private ArrayList<RecommendedListResponse.Data.RecommendedItems> recommendedListResponses;
    private Activity context;
    private int type;

    private HomeRecommendedPlacesSectionClickListener homeRecommendedPlacesSectionClickListener;

    public interface HomeRecommendedPlacesSectionClickListener
    {
        void setOnClickListener(int position, int typeName);
    }

    public HomeRecommendedPlacesAdapter(Activity context, ArrayList<RecommendedListResponse.Data.RecommendedItems> recommendedListResponses, HomeRecommendedPlacesSectionClickListener homeRecommendedPlacesSectionClickListener, int type)
    {
        this.context = context;
        this.recommendedListResponses = recommendedListResponses;
        this.homeRecommendedPlacesSectionClickListener = homeRecommendedPlacesSectionClickListener;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == TYPE_PROGRESS)
        {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);

            return new ProgressViewHolder(itemView);
        }
        else
        {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_view_layout, parent, false);

            return new RecommendedHomeViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        if(holder instanceof RecommendedHomeViewHolder)
        {
            final RecommendedHomeViewHolder recommendedHomeViewHolder = (RecommendedHomeViewHolder)holder;
            RecommendedListResponse.Data.RecommendedItems recommendedItems = recommendedListResponses.get(position);
            if(position == (recommendedListResponses.size() - 1))
            {
                recommendedHomeViewHolder.views.setVisibility(View.GONE);
            }
            else
            {
                recommendedHomeViewHolder.views.setVisibility(View.VISIBLE);
            }

            if(!AppUtil.isStringEmpty(recommendedItems.getImage()))
            {
                Picasso.with(context).load(recommendedItems.getImage()).into(recommendedHomeViewHolder.imageViewCity);
            }

            recommendedHomeViewHolder.textViewCityName.setText(recommendedItems.getName());
            recommendedHomeViewHolder.textViewSearchCity.setText(recommendedItems.getCityName());
            recommendedHomeViewHolder.textViewSearchState.setText(recommendedItems.getStateName());
            recommendedHomeViewHolder.textViewPrice.setText(context.getResources().getString(R.string.lable_prices) + " " + recommendedItems.getPrice());

            OnClickHandler onClickHandler = new OnClickHandler();
            recommendedHomeViewHolder.itemView.setTag(position);
            recommendedHomeViewHolder.itemView.setOnClickListener(onClickHandler);
        }
        else if(holder instanceof ProgressViewHolder)
        {
            ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
            progressViewHolder.progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount()
    {
        return AppUtil.isCollectionEmpty(recommendedListResponses) ? 0 : recommendedListResponses.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return AppUtil.isCollectionEmpty(recommendedListResponses) ? TYPE_PROGRESS : TYPE_ITEM;
    }

    //View Holder Get The Ids Of View
    protected static class RecommendedHomeViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.textViewCityName)
        TextView textViewCityName;
        @Bind(R.id.imageViewCity)
        ImageView imageViewCity;
        @Bind(R.id.textViewSearchCity)
        TextView textViewSearchCity;
        @Bind(R.id.textViewSearchState)
        TextView textViewSearchState;
        @Bind(R.id.textViewPrice)
        TextView textViewPrice;

        @Bind(R.id.views)
        View views;

        public RecommendedHomeViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.progressBar)
        ProgressBar progressBar;

        public ProgressViewHolder(View v)
        {
            super(v);

            ButterKnife.bind(this, v);
        }
    }

    /**
     * Set On Click Handler Listener
     */
    private class OnClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Integer integer = (Integer) view.getTag();
            if(integer != null)
            {
                homeRecommendedPlacesSectionClickListener.setOnClickListener(integer, type);
            }
        }
    }
}