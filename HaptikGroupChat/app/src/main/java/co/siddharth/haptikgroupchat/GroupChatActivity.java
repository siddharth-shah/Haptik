package co.siddharth.haptikgroupchat;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import co.siddharth.haptikgroupchat.chat.GroupChatFragment;
import co.siddharth.haptikgroupchat.data.GroupChatContainer;
import co.siddharth.haptikgroupchat.databinding.ActivityMainBinding;
import co.siddharth.haptikgroupchat.favoritesinfo.GroupChatFavoritesFragment;

public class GroupChatActivity extends AppCompatActivity implements GroupChatFragment.OnFragmentInteractionListener, GroupChatFavoritesFragment.OnFragmentInteractionListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GroupChatContainer.instantiateGroupContainer();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Group Conversations");
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.view.PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager);
        binding.chatViewPager.setAdapter(pagerAdapter);
        binding.groupChatTabs.setupWithViewPager(binding.chatViewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class PagerAdapter extends FragmentPagerAdapter {
        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return GroupChatFragment.newInstance();
                case 1:
                    return GroupChatFavoritesFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Chat";
                case 1:
                    return "Conversation";
            }
            return super.getPageTitle(position);
        }
    }

}
