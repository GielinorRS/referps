package com.ruse.world.content.youtube;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class YoutubeChannelInfo {
	
	@Getter
	private String channelId;
	
	@Getter
	private String name;
	
	@Getter
	private String profilePic;
	
	@Getter
	private long subscribers;
	
	@Getter
	private String uploadsPlaylist;

	public void updateChannelInfo(String name, long subscribers, String profilePic, String uploadsPlaylist) {
		this.name = name;
		this.subscribers = subscribers;
		this.profilePic = profilePic;
		this.uploadsPlaylist = uploadsPlaylist;
	}
}
