package com.ruse.world.content.youtube;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class YoutubeVideoDetails {
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private String thumbnailUrl;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private Date publishedDate;
	
	@Getter
	@Setter
	private long likeCount;
	
	@Getter
	@Setter
	private long viewCount;
	
	@Getter
	@Setter
	private long commentCount;
	
	@Getter
	@Setter
	private String videoId;
	
	@Getter
	@Setter
	private String channelId;
	
	@Getter
	@Setter
	private String channelName;
	
	
}
