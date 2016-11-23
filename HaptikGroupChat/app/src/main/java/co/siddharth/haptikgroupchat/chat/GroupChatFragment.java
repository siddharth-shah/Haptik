package co.siddharth.haptikgroupchat.chat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.siddharth.haptikgroupchat.databinding.FragmentGroupChatBinding;
import co.siddharth.haptikgroupchat.events.FetchData;
import co.siddharth.haptikgroupchat.Constants;


public class GroupChatFragment extends Fragment {
    GroupChatAdapter adapter;
    ChatPresenter chatPresenter;

    private OnFragmentInteractionListener mListener;
    private FragmentGroupChatBinding binding;

    public GroupChatFragment() {
    }


    public static GroupChatFragment newInstance() {
        return new GroupChatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatPresenter = new ChatPresenter();
        chatPresenter.loadChats();
        adapter = new GroupChatAdapter(new ArrayList<ChatViewModel>(0));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGroupChatBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        binding.groupChatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.groupChatList.setHasFixedSize(true);
        binding.groupChatList.setAdapter(adapter);
        return rootView;
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
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);
    }

    @org.greenrobot.eventbus.Subscribe
    public void updateData(FetchData fetchData) {
        if (fetchData.getStatus() == Constants.FETCH_DATA_SUCCESS) {
            adapter.replaceData(chatPresenter.getChatItems());
        } else if (fetchData.getStatus() == Constants.NO_INTERNET) {
            Snackbar.make(binding.getRoot(), "Oops, no internet!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chatPresenter.loadChats();
                }
            }).show();
        } else if (fetchData.getStatus() == Constants.NO_DATA) {
            binding.groupChatList.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
        }
    }

    class GroupChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        private List<ChatViewModel> chatItemList;

        GroupChatAdapter(List<ChatViewModel> chatItemList) {
            this.chatItemList = chatItemList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ChatViewHolder.create(getLayoutInflater(null), parent);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            ((ChatViewHolder) holder).bindTo(chatItemList.get(position));
            handleVisibility(position, ((ChatViewHolder) holder));
            ((ChatViewHolder) holder).mBinding.card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (chatItemList.get(position).isFavorite()) {
                        ((ChatViewHolder) holder).mBinding.favoriteToggle.setSelected(false);
                        chatItemList.get(position).setFavorite(false);
                    } else {
                        ((ChatViewHolder) holder).mBinding.favoriteToggle.setSelected(true);
                        chatItemList.get(position).setFavorite(true);
                    }
                    handleVisibility(position, (ChatViewHolder) holder);
                    chatPresenter.changeItemAt(position, chatItemList.get(position));
                    return true;
                }
            });
        }

        private void handleVisibility(int position, ChatViewHolder holder) {
            if (!chatItemList.get(position).isFavorite()) {
                holder.mBinding.favoriteToggle.setVisibility(View.GONE);

            } else {
                holder.mBinding.favoriteToggle.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public int getItemCount() {
            return chatItemList.size();
        }

        public void replaceData(List<ChatViewModel> chatItems) {
            this.chatItemList = chatItems;
            notifyDataSetChanged();
        }
    }

}

