package com.youtubeplaylistallclasses;

/**
 * Created by ashiq on 30-Nov-16.
 */

/**
 * Interface used by the {@link RecyclerYoutubePlaylistAdapter} to inform us that we reached the last item in the list.
 */
public interface LastItemReachedListener {
    void onLastItem(int position, String nextPageToken);

}

