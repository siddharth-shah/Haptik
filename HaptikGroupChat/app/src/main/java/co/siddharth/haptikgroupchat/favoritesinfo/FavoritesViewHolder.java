package co.siddharth.haptikgroupchat.favoritesinfo;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import co.siddharth.haptikgroupchat.R;
import co.siddharth.haptikgroupchat.databinding.FavoriteListItemBinding;

/**
 * Created by siddharth on 23/11/16.
 */

public class FavoritesViewHolder extends RecyclerView.ViewHolder {
    FavoriteListItemBinding mBinding;

    public FavoritesViewHolder(FavoriteListItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }


    static FavoritesViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        FavoriteListItemBinding binding = FavoriteListItemBinding.inflate(inflater, parent, false);
        return new FavoritesViewHolder(binding);
    }

    void bindTo(FavoriteInfoModel favoriteInfoModel) {
        mBinding.setFavorite(favoriteInfoModel);

    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (imageUrl.isEmpty()) {
            Picasso.with(view.getContext()).load(R.mipmap.placeholder).into(view);
        } else {
            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .into(view);
        }
    }

}
