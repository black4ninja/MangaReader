package com.black4world.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.IconicIcon;
import com.black4world.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 8/07/13
 * Time: 04:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MangaEdenAdapter extends BaseAdapter {

    private static final String TAG = MangaEdenAdapter.class.getSimpleName();
    private String[] mangas;
    private String[] info;
    private Context context;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;
    private LinearLayout ll;
    private boolean state_color;

    public MangaEdenAdapter(String data,Context context) {
        this.mangas = data.split("#####");
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
                .build();

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.lala)
                .showImageForEmptyUri(R.drawable.blogheader)
                .showImageOnFail(R.drawable.no_image)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoader.getInstance().init(config);

        this.context = context;
    }

    @Override
    public int getCount() {
        return mangas.length;
    }

    @Override
    public Object getItem(int position) {
        return mangas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.manga_eden_row, parent, false);
        }
            TextView titulo = (TextView) view.findViewById(R.id.title_me);
            View status = (View) view.findViewById(R.id.status_me);
            TextView hits = (TextView) view.findViewById(R.id.hits_me);
            ImageView cover = (ImageView) view.findViewById(R.id.cover_me);
            ll = (LinearLayout) view.findViewById(R.id.manga_info);

            if(index % 2 == 0){
                state_color = true;
            }else{
                state_color = false;
            }

            info = mangas[index].split(",");
            if(state_color){
                view.setBackgroundColor(Color.BLACK);
                titulo.setText(info[1]);
                titulo.setTextColor(Color.WHITE);

                for (int i = 2; i <info.length ; i++) {
                    if(info[i].contains(".jpg") || info[i].contains(".png")){
                        ImageLoader.getInstance().displayImage("http://cdn.mangaeden.com/mangasimg/"+info[i], cover, options, animateFirstListener);
                    }else if(info[i].length() == 1){
                        if(Integer.parseInt(info[i]) == 1){
                            IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
                            iconicFontDrawable.setIcon(IconicIcon.BOOK_OPEN);
                            iconicFontDrawable.setIconColor(Color.WHITE);
                            if (SDK_INT < JELLY_BEAN) {
                                status.setBackgroundDrawable(iconicFontDrawable);
                            } else {
                                status.setBackground(iconicFontDrawable);
                            }

                        }else{
                            IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
                            iconicFontDrawable.setIcon(IconicIcon.BOOK);
                            iconicFontDrawable.setIconColor(Color.WHITE);
                            //status.setBackground(iconicFontDrawable);
                            if (SDK_INT < JELLY_BEAN) {
                                status.setBackgroundDrawable(iconicFontDrawable);
                            } else {
                                status.setBackground(iconicFontDrawable);
                            }
                        }
                    }if(info[i].length() == 5){
                        hits.setText("Hits: "+info[i]);
                        hits.setTextColor(Color.WHITE);
                    }
                }


            }else{
                view.setBackgroundColor(Color.WHITE);
                titulo.setText(info[1]);
                titulo.setTextColor(Color.BLACK);

                for (int i = 0; i <info.length ; i++) {
                    if(info[i].contains(".jpg") || info[i].contains(".png")){
                        ImageLoader.getInstance().displayImage("http://cdn.mangaeden.com/mangasimg/"+info[i], cover, options, animateFirstListener);
                    }else if(info[i].length() == 1){
                        if(Integer.parseInt(info[i]) == 1){
                            IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
                            iconicFontDrawable.setIcon(IconicIcon.BOOK_OPEN);
                            iconicFontDrawable.setIconColor(Color.BLACK);
                            if (SDK_INT < JELLY_BEAN) {
                                status.setBackgroundDrawable(iconicFontDrawable);
                            } else {
                                status.setBackground(iconicFontDrawable);
                            }

                        }else{
                            IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
                            iconicFontDrawable.setIcon(IconicIcon.BOOK);
                            iconicFontDrawable.setIconColor(Color.BLACK);
                            if (SDK_INT < JELLY_BEAN) {
                                status.setBackgroundDrawable(iconicFontDrawable);
                            } else {
                                status.setBackground(iconicFontDrawable);
                            }

                        }
                    }if(info[i].length() == 5 ){
                        try{
                        hits.setText("Hits: "+Integer.parseInt(info[i]));
                        hits.setTextColor(Color.BLACK);
                        }catch (NumberFormatException e){
                            break;
                        }
                    }
                }
            }

        return view;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
