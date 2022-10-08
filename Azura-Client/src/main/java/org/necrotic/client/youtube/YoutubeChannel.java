package org.necrotic.client.youtube;


public class YoutubeChannel {

	public YoutubeChannel(String channelId, String name, long subscribers, String profilePic) {
		this.channelId = channelId;
		this.name = name;
		this.subscribers = subscribers;
		this.profilePic = profilePic;
	}
	
	private final String channelId;
	
	public String getChannelId() {
		return channelId;
	}
	
	private final String name;

	public String getName() {
		return name;
	}
	
	private final long subscribers;
	
	public long getSubscribers() {
		return subscribers;
	}
	
	private final String profilePic;

	public String getProfilePic() {
		return profilePic;
	}
}
