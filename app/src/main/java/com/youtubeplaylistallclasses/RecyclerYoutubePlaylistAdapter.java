package com.youtubeplaylistallclasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoContentDetails;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatistics;
import com.hgwcapp.hgwcofficialapp.R;
import com.hgwcapp.hgwcofficialapp.VideoLister;

import java.text.DecimalFormat;


/**
 * Created by ashiq on 27-Nov-16.
 */

public class RecyclerYoutubePlaylistAdapter extends RecyclerView.Adapter<RecyclerYoutubePlaylistAdapter.ViewHolder> {

    private static final DecimalFormat sFormatter = new DecimalFormat("#,###,###");
    private final PlaylistVideos mPlaylistVideos;
    private final LastItemReachedListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final Context mContext;
        public final TextView mTitleText;

        //public final TextView mDescriptionText;
        //public final ImageView mThumbnailImage;
        //public final ImageView mShareIcon;
        //public final TextView mShareText;
        //public final TextView mDurationText;
        //public final TextView mViewCountText;
        //public final TextView mLikeCountText;
        //public final TextView mDislikeCountText;

        public ViewHolder(View v) {
            super(v);
            mContext = v.getContext();
            mTitleText = (TextView) v.findViewById(R.id.tvTitle);

            //mDurationText = (TextView) v.findViewById(R.id.video_dutation_text);
            //mThumbnailImage = (ImageView) v.findViewById(R.id.video_thumbnail);
            //mDescriptionText = (TextView) v.findViewById(R.id.video_description);
            //mShareIcon = (ImageView) v.findViewById(R.id.video_share);
            // mShareText = (TextView) v.findViewById(R.id.video_share_text);
            //mViewCountText= (TextView) v.findViewById(R.id.video_view_count);
            //mLikeCountText = (TextView) v.findViewById(R.id.video_like_count);
            //mDislikeCountText = (TextView) v.findViewById(R.id.video_dislike_count);
        }
    }

    public RecyclerYoutubePlaylistAdapter(PlaylistVideos PlaylistVideos, LastItemReachedListener lastItemReachedListener) {
        mPlaylistVideos = PlaylistVideos;
        mListener = lastItemReachedListener;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflate a card layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_list_items, parent, false);
        // populate the viewholder
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (mPlaylistVideos.size() == 0) {
            return;
        }

        final Video video = mPlaylistVideos.get(position);
        final VideoSnippet videoSnippet = video.getSnippet();
        final VideoContentDetails videoContentDetails = video.getContentDetails();
        final VideoStatistics videoStatistics = video.getStatistics();

        holder.mTitleText.setText(videoSnippet.getTitle());
        holder.mTitleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video.getId())));
            }
        });
        // holder.mTitleText.setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View view) {
        //   holder.mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video.getId())));
        //}
        //});

        // create and set the click listener for both the share icon and share text
        View.OnClickListener shareClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch \"" + videoSnippet.getTitle() + "\" on YouTube");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + video.getId());
                sendIntent.setType("text/plain");
                holder.mContext.startActivity(sendIntent);
            }
        };

        if (mListener != null) {
            // get the next playlist page if we're at the end of the current page and we have another page to get
            final String nextPageToken = mPlaylistVideos.getNextPageToken();
            if (!isEmpty(nextPageToken) && position == mPlaylistVideos.size() - 1) {

                holder.itemView.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onLastItem(position, nextPageToken);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return mPlaylistVideos.size();
    }

    private boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {

            return true;
        }
        return false;
    }


}
