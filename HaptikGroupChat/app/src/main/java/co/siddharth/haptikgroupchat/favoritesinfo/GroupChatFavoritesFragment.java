package co.siddharth.haptikgroupchat.favoritesinfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import co.siddharth.haptikgroupchat.Constants;
import co.siddharth.haptikgroupchat.databinding.FragmentGroupChatFavoritesBinding;
import co.siddharth.haptikgroupchat.events.FetchData;
import co.siddharth.haptikgroupchat.events.ItemChange;


public class GroupChatFavoritesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FragmentGroupChatFavoritesBinding mBinding;
    FavoritesPresenter favoritesPresenter;
    FavoritesListAdapter favoritesListAdapter;

    public GroupChatFavoritesFragment() {
        // Required empty public constructor
    }

    public static GroupChatFavoritesFragment newInstance() {
        GroupChatFavoritesFragment fragment = new GroupChatFavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoritesPresenter = new FavoritesPresenter();
        favoritesListAdapter = new FavoritesListAdapter(new ArrayList<FavoriteInfoModel>(0));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentGroupChatFavoritesBinding.inflate(inflater, container, false);
        mBinding.favoriteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.favoriteList.setAdapter(favoritesListAdapter);
        return mBinding.getRoot();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @org.greenrobot.eventbus.Subscribe
    public void loadData(FetchData fetchData) {
        if (fetchData.getStatus() == Constants.FETCH_DATA_SUCCESS) {
            favoritesListAdapter.replaceData(favoritesPresenter.getFavoriteInfo());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @org.greenrobot.eventbus.Subscribe
    public void itemChange(ItemChange itemChange) {
        favoritesListAdapter.replaceData(favoritesPresenter.getFavoriteInfo());
    }

    class FavoritesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<FavoriteInfoModel> favoriteInfoModelList;

        FavoritesListAdapter(List<FavoriteInfoModel> favoriteInfoModelList) {
            this.favoriteInfoModelList = favoriteInfoModelList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return FavoritesViewHolder.create(getLayoutInflater(null), parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((FavoritesViewHolder) holder).bindTo(favoriteInfoModelList.get(position));
        }

        @Override
        public int getItemCount() {
            return favoriteInfoModelList.size();
        }

        public void replaceData(List<FavoriteInfoModel> favoriteInfo) {
            this.favoriteInfoModelList = favoriteInfo;
            notifyDataSetChanged();
        }
    }

}
