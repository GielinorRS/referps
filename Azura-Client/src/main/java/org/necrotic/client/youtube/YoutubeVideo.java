package org.necrotic.client.youtube;


public class YoutubeVideo {

	public YoutubeVideo(String videoId, String name, int views, int likes, int comments,
			String channelName, String channelId, String date) {
		this.videoId = videoId;
		this.name = name;
		this.views = views;
		this.likes = likes;
		this.comments = comments;
		this.channelName = channelName;
		this.channelId = channelId;
		this.date = date;
	}
	private final String date;

	public String getDate() {
		return date;
	}

	private final String videoId;

	public String getVideoId() {
		return videoId;
	}

	private final String channelId;

	public String getChannelId() {
		return channelId;
	}

	private final String name;

	public String getName() {
		return name;
	}

	private final int views;

	public int getViews() {
		return views;
	}

	private final int likes;

	public int getLikes() {
		return likes;
	}

	private final int comments;

	public int getComments() {
		return comments;
	}
	
	private final String channelName;
	
	public String getChannelName() {
		return channelName;
	}

}
