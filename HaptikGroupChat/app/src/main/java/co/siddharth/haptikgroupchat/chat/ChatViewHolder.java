package co.siddharth.haptikgroupchat.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import co.siddharth.haptikgroupchat.databinding.ChatItemBinding;

/**
 * Created by siddharth on 22/11/16.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder {
    ChatItemBinding mBinding;

    private ChatViewHolder(ChatItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;

    }

    public static ChatViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        ChatItemBinding binding = ChatItemBinding.inflate(inflater, parent, false);
        return new ChatViewHolder(binding);
    }

    public void bindTo(ChatViewModel chatViewModel) {
        mBinding.setChat(chatViewModel);
//        mBinding.favoriteToggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (view.isSelected()) {
//                    view.setSelected(false);
//                } else {
//                    view.setSelected(true);
//                }
//            }
//        });
        mBinding.executePendingBindings();
    }
}