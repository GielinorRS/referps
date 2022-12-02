package com.ruse.world.content.youtube;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Getter;

public class YoutubeMgr {

	private static YouTube youtube;

	@Getter
	private static final List<YoutubeChannelInfo> channels = new ArrayList<>();

	@Getter
	private static final List<YoutubeVideoDetails> videos = new ArrayList<>();

	private static void loadChannels() {
		channels.clear();
		File file = new File("./data/saves/youtube/channels.json");
		try (FileReader fileReader = new FileReader(file)) {
			JsonParser fileParser = new JsonParser();
			Gson builder = new GsonBuilder().create();
			JsonObject reader = (JsonObject) fileParser.parse(fileReader);
			if (reader.has("channels")) {
				YoutubeChannelInfo[] savedChannels = builder.fromJson(reader.get("channels").getAsJsonArray(),
						YoutubeChannelInfo[].class);
				channels.addAll(Arrays.asList(savedChannels));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void reloadAllVideos() {
		videos.clear();
		for (YoutubeChannelInfo channel : channels) {
			try {
				loadVideosFromChannel(channel.getUploadsPlaylist());
			} catch (Exception e) {
				System.out.println("Error loading video from channel: "+channel.getChannelId());
			}
		}
		videos.sort((video1, video2) -> {
			long value1 = video1.getPublishedDate().getTime();
			long value2 = video2.getPublishedDate().getTime();
			return Long.compare(value2, value1);
		});
		System.out.println("Successfully updated " + videos.size() + " Youtube videos!");
	}

	public static void load() {
		try {
			youtube = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(),
					JacksonFactory.getDefaultInstance(), request -> {
			}).setApplicationName("YoutubeVideoInfo").setYouTubeRequestInitializer(
					new YouTubeRequestInitializer("AIzaSyDNIp0zWfShy7lD0T8Uva3Cd6sKx0YmUeE")).build();

		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
		loadChannels();


		new Thread(() -> {
			updateChannels();
			reloadAllVideos();
		}).start();

	}

	private static void updateChannels() {
		for (YoutubeChannelInfo c : channels) {
			updateChannel(c);
		}
		/*channels.sort((channel1, channel2) -> {
			long value1 = channel1.getSubscribers();
			long value2 = channel2.getSubscribers();
			return Long.compare(value1, value2);
		});*/
		System.out.println("Successfully updated " + channels.size() + " Youtube channels!");
	}

	private static void updateChannel(YoutubeChannelInfo channel) {
		try {
			YouTube.Channels.List channelsList = youtube.channels().list("snippet, statistics, contentDetails");

			channelsList.setId(channel.getChannelId());
			ChannelListResponse channelResponse = channelsList.execute();
			Channel c = channelResponse.getItems().get(0);

			channel.updateChannelInfo(c.getSnippet().getTitle(),
					c.getStatistics().getSubscriberCount().longValue(),
					c.getSnippet().getThumbnails().getDefault().getUrl(),
					c.getContentDetails().getRelatedPlaylists().getUploads());

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error with channel: " + channel.getChannelId());
		}
	}

	public static void loadVideoFromId(String id) {
		try {
			YouTube.Videos.List list = youtube.videos().list("snippet, statistics");
			list.setId(id);
			Video v = list.execute().getItems().get(0);

			int get = -1;
			for (int i = 0; i < videos.size(); i++) {
				YoutubeVideoDetails s = videos.get(i);
				if (s.getChannelId().equals(id)) {
					get = i;
				}
			}
			YoutubeVideoDetails details = get > -1 ? videos.get(get) : new YoutubeVideoDetails();
			details.setVideoId(id);
			details.setTitle(v.getSnippet().getTitle());
			details.setDescription(v.getSnippet().getDescription());
			details.setThumbnailUrl(v.getSnippet().getThumbnails().getDefault().getUrl());
			details.setPublishedDate(new Date(v.getSnippet().getPublishedAt().getValue()));
			details.setLikeCount(
					v.getStatistics().getLikeCount() != null ? v.getStatistics().getLikeCount().longValue() : 0);
			details.setCommentCount(
					v.getStatistics().getCommentCount() != null ? v.getStatistics().getCommentCount().longValue() : 0);
			details.setViewCount(
					v.getStatistics().getViewCount() != null ? v.getStatistics().getViewCount().longValue() : 0);
			details.setChannelId(v.getSnippet().getChannelId());
			details.setChannelName(v.getSnippet().getChannelTitle());
			if (get == -1) {
				videos.add(details);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadVideosFromChannel(String uploadLink) {
		try {
			YouTube.PlaylistItems.List playlistItemRequest = youtube.playlistItems().list("snippet, contentDetails");
			playlistItemRequest.setFields("nextPageToken,items/snippet/title,items/snippet/description,items/contentDetails/videoId");
			playlistItemRequest.setMaxResults(100L);
			playlistItemRequest.setPlaylistId(uploadLink);

			PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

			for (PlaylistItem item : playlistItemResult.getItems()) {
				String title = item.getSnippet().getTitle().toLowerCase();
				String desc = item.getSnippet().getDescription();

				if (title.contains("lunite")) {
					loadVideoFromId(item.getContentDetails().getVideoId());
				}else if (desc != null && desc.contains("lunite")){
					loadVideoFromId(item.getContentDetails().getVideoId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}