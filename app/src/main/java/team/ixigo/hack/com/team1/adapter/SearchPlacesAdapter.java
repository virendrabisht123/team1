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
import team.ixigo.hack.com.team1.model.response.SearchResponse;
import team.ixigo.hack.com.team1.utility.AppUtil;

public class SearchPlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_PROGRESS = 2;
    private ArrayList<SearchResponse> listArrayList;
    private Activity context;

    private SearchPlacesSectionClickListener searchPlacesSectionClickListener;

    public interface SearchPlacesSectionClickListener
    {
        void setOnClickListener(int position, String typeName);
    }

    public SearchPlacesAdapter(Activity context, ArrayList<SearchResponse> listArrayList, SearchPlacesSectionClickListener placesSectionClickListener)
    {
        this.context = context;
        this.listArrayList = listArrayList;
        this.searchPlacesSectionClickListener = placesSectionClickListener;
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
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_layout, parent, false);

            return new SearchLocationViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        if(holder instanceof SearchLocationViewHolder)
        {
            final SearchLocationViewHolder recommendedHomeViewHolder = (SearchLocationViewHolder)holder;
            SearchResponse list = listArrayList.get(position);
            if(position == (listArrayList.size() - 1))
            {
                recommendedHomeViewHolder.views.setVisibility(View.GONE);
            }
            else
            {
                recommendedHomeViewHolder.views.setVisibility(View.VISIBLE);
            }

            if(!AppUtil.isStringEmpty(list.getUrl()))
            {
                Picasso.with(context).load(list.getUrl()).into(recommendedHomeViewHolder.imageViewCity);
            }

            recommendedHomeViewHolder.textViewCityName.setText(list.getCityName());
            recommendedHomeViewHolder.textViewSearchCountry.setText(list.getCountry());
            recommendedHomeViewHolder.textViewSearchState.setText(list.getState());
            recommendedHomeViewHolder.textViewAddress.setText(list.getAddress());

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
        return AppUtil.isCollectionEmpty(listArrayList) ? 0 : listArrayList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return AppUtil.isCollectionEmpty(listArrayList) ? TYPE_PROGRESS : TYPE_ITEM;
    }

    //View Holder Get The Ids Of View
    protected static class SearchLocationViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.textViewCityName)
        TextView textViewCityName;
        @Bind(R.id.imageViewCity)
        ImageView imageViewCity;
        @Bind(R.id.textViewSearchState)
        TextView textViewSearchState;
        @Bind(R.id.textViewSearchCountry)
        TextView textViewSearchCountry;
        @Bind(R.id.textViewAddress)
        TextView textViewAddress;

        @Bind(R.id.views)
        View views;

        public SearchLocationViewHolder(View itemView)
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