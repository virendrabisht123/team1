package team.ixigo.hack.com.team1.utility;

import java.util.Comparator;

import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;

/**
 * Created by virendrapalsingh on 9/4/17.
 */

public class FilterUtility
{
    public static class PriceComparator implements Comparator
    {
        public int compare(Object o1,Object o2)
        {
            RecommendedListResponse.Data.RecommendedItems s1 = (RecommendedListResponse.Data.RecommendedItems)o1;
            RecommendedListResponse.Data.RecommendedItems s2 = (RecommendedListResponse.Data.RecommendedItems)o2;
            double price1 = AppUtil.convertInDouble(s1.getPrice());
            double price2 = AppUtil.convertInDouble(s2.getPrice());

            if(price1 == price2)
                return 0;
            else if(price1 > price2)
                return 1;
            else
                return -1;
        }
    }
}
