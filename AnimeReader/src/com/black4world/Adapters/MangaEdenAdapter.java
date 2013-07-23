package com.black4world.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.black4world.Basic.Manga;
import com.black4world.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import org.json.JSONObject;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 8/07/13
 * Time: 04:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MangaEdenAdapter extends BaseAdapter {

    private static final String TAG = MangaEdenAdapter.class.getSimpleName();
    private  Vector<Manga> listArray = new Vector<Manga>();
    private  Vector<JSONObject> mangasData;

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;
    static final String[] urls = new String[] {
            "http://cdn.mangaeden.com/mangasimg/a2/a2e5af8073b16751553de2869d66316638e33400f774354213c014ac.jpg",
            "http://cdn.mangaeden.com/mangasimg/92/921a1df16c23e0e13fa05471a096fadd1848284a245d3a03c06985e6.jpg",
            "http://cdn.mangaeden.com/mangasimg/c1/c1a2e639e5d3316e4aa464a092119a1967c86de4f437347c781deeba.jpg",
            "http://cdn.mangaeden.com/mangasimg/79/79a56c7fe0bb0dc9cbe9ccc71083102e9090a251acd8a2ba68febe9f.jpg",
            "http://cdn.mangaeden.com/mangasimg/bd/bda9f6af19ea9e7f19f5f10aca0b9efba42fb42098966f6bd70090c9.jpg",
            "http://cdn.mangaeden.com/mangasimg/81/814212af8ba1cbdfebccdd32495d127a766550e6bf9bf5a6a97f269f.png",
            "http://cdn.mangaeden.com/mangasimg/ad/ad7db40ebd8444355421da1f472b05ae71008078054d49a59d90548c.png"};

    public MangaEdenAdapter(Context context) {
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
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoader.getInstance().init(config);


    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.manga_eden_row, parent, false);
            TextView titulo = (TextView) view.findViewById(R.id.title_me);
            ImageView cover = (ImageView) view.findViewById(R.id.cover_me);

            //titulo.setText(dataModel.getTitle());
            //cover.setImageResource(dataModel.getImage());
            //cover.setImageResource(R.drawable.lala);
            ImageLoader.getInstance().displayImage(urls[index], cover, options, animateFirstListener);
            titulo.setText(urls[index]);
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
