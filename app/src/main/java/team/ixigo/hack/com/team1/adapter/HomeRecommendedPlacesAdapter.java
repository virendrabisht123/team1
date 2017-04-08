package team.ixigo.hack.com.team1.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import team.ixigo.hack.com.team1.R;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;
import team.ixigo.hack.com.team1.utility.AppUtil;

public class HomeRecommendedPlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_PROGRESS = 2;
    private ArrayList<RecommendedListResponse> recommendedListResponses;
    private Activity context;

    private HomeRecommendedPlacesSectionClickListener homeRecommendedPlacesSectionClickListener;

    public interface HomeRecommendedPlacesSectionClickListener
    {
        void setOnClickListener(int position, String typeName);
    }

    public HomeRecommendedPlacesAdapter(Activity context, ArrayList<RecommendedListResponse> recommendedListResponses, HomeRecommendedPlacesSectionClickListener homeRecommendedPlacesSectionClickListener)
    {
        this.context = context;
        this.recommendedListResponses = recommendedListResponses;
        this.homeRecommendedPlacesSectionClickListener = homeRecommendedPlacesSectionClickListener;
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

            return new HomeShopViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        if(holder instanceof HomeShopViewHolder)
        {
            final HomeShopViewHolder homeShopViewHolder = (HomeShopViewHolder)holder;
            RecommendedListResponse recommendedListResponse = recommendedListResponses.get(position);

            if(position == (recommendedListResponses.size() - 1))
            {
                homeShopViewHolder.views.setVisibility(View.GONE);
            }
            else
            {
                homeShopViewHolder.views.setVisibility(View.VISIBLE);
            }

            OnClickHandler onClickHandler = new OnClickHandler();
            homeShopViewHolder.itemView.setTag(position);
            homeShopViewHolder.itemView.setOnClickListener(onClickHandler);
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
    protected static class HomeShopViewHolder extends RecyclerView.ViewHolder
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

        public HomeShopViewHolder(View itemView)
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
        }
    }
}