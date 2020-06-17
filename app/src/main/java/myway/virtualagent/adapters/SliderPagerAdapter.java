package myway.virtualagent.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import myway.virtualagent.R;
import myway.virtualagent.fragments.HomeFragment;
import myway.virtualagent.models.Slide;

public class SliderPagerAdapter extends PagerAdapter {

    private Context mContext ;
    private List<Slide> lstSlides ;


   /* public SliderPagerAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }*/

    public SliderPagerAdapter(Context context, List<Slide> lstSlides) {

        this.lstSlides = lstSlides;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View slideLayout = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_item, container, false);

//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // View slideLayout = inflater.inflate(R.layout.slide_item,null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        slideImg.setImageResource(lstSlides.get(position).getImage());
        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return lstSlides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}