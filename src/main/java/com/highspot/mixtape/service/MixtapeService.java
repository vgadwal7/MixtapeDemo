package com.highspot.mixtape.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.highspot.mixtape.pojo.Changes;
import com.highspot.mixtape.pojo.Mixtape;
import com.highspot.mixtape.pojo.Playlist;
import com.highspot.mixtape.pojo.Song;
import com.highspot.mixtape.pojo.User;

/**
 * Service class for the Mixtape changes
 * 
 * @author VG
 *
 */
public class MixtapeService {

	/**
	 * process the changes file for the mixtape
	 * 
	 * @param sourceFileName
	 * @param changesFileName
	 * @param outputFileName
	 * @throws Exception
	 */
	public static void processChanges(String sourceFileName, String changesFileName, String outputFileName)
			throws Exception {

		try {
			File mixtapeFile = new File(MixtapeDemoConstants.FILE_PATH + sourceFileName);
			JsonReader reader = new JsonReader(new FileReader(mixtapeFile));
			Mixtape mixtape = new Gson().fromJson(reader, Mixtape.class);
			File changesFile = new File(MixtapeDemoConstants.FILE_PATH + changesFileName);
			reader = new JsonReader(new FileReader(changesFile));
			Changes changes = new Gson().fromJson(reader, Changes.class);
			processActionsinChangeFile(mixtape, changes);
			writeResultToOutputFile(mixtape, outputFileName);

		} catch (Exception e) {
			throw e;

		}

	}

	/**
	 * write the result of the processing to the output.json file
	 * 
	 * @param mixtape
	 * @throws IOException
	 */
	private static void writeResultToOutputFile(Mixtape mixtape, String outputFileName) throws IOException {
		Path outputFilePath = Paths.get(MixtapeDemoConstants.FILE_PATH + outputFileName);
		if (!Files.exists(outputFilePath)) {
			Files.createFile(outputFilePath);
		}
		try (Writer writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {
			Gson gson = new Gson();
			gson.toJson(mixtape, writer);
		}
	}

	/**
	 * process actions for songs and playlists in the changes file
	 * 
	 * @param mixtape
	 * @param changes
	 */
	private static void processActionsinChangeFile(Mixtape mixtape, Changes changes) {
		Map<String, Playlist> idtoPlayLists = mixtape.getPlaylists().stream()
				.collect(Collectors.toMap(Playlist::getId, playlist -> playlist));

		Map<String, User> idtoUsers = mixtape.getUsers().stream().collect(Collectors.toMap(User::getId, user -> user));

		Map<String, Song> idtoSongs = mixtape.getSongs().stream().collect(Collectors.toMap(Song::getId, song -> song));

		for (Playlist playlist : changes.getPlaylists()) {

			if (idtoPlayLists.containsKey(playlist.getId()) && !playlist.getId().isEmpty()) {

				// Removes a playlist
				if (playlist.getAction().equals(MixtapeDemoConstants.REMOVE)) {
					idtoPlayLists.remove(playlist.getId());
				}

				// Adds new songs
				if (playlist.getAction().equals(MixtapeDemoConstants.ADD)) {
					addSongs(idtoPlayLists, idtoSongs, playlist);
				}
			} // Adds a new playlist
			else if (playlist.getId().isEmpty() && playlist.getAction().equals(MixtapeDemoConstants.ADD)) {
				addNewPlaylist(idtoPlayLists, idtoUsers, idtoSongs, playlist);
			}

		}

		List<Playlist> revisedPlayListEntries = new ArrayList<Playlist>(idtoPlayLists.values());
		mixtape.setPlaylists(revisedPlayListEntries);
	}

	/**
	 * add new playlists
	 * 
	 * @param idtoPlayLists
	 * @param idtoUsers
	 * @param idtoSongs
	 * @param playlist
	 */
	private static void addNewPlaylist(Map<String, Playlist> idtoPlayLists, Map<String, User> idtoUsers,
			Map<String, Song> idtoSongs, Playlist playlist) {
		if (idtoUsers.containsKey(playlist.getUserId())) {
			Set<String> existingSongs = new HashSet<>();
			for (String songId : playlist.getSongIds()) {
				if (idtoSongs.containsKey(songId)) {
					existingSongs.add(songId);
				}
			}

			if (!existingSongs.isEmpty()) {
				Playlist new_playlist = new Playlist();
				new_playlist.setSongIds(new ArrayList<String>(existingSongs));
				new_playlist.setUserId(playlist.getUserId());
				String maxKey = Collections.max(idtoPlayLists.keySet());
				String playlistId = "" + (Integer.parseInt(maxKey) + 1);
				new_playlist.setId(playlistId);
				idtoPlayLists.put(playlistId, new_playlist);
			}

		}
	}

	/**
	 * add new songs
	 * 
	 * @param idtoPlayLists
	 * @param idtoSongs
	 * @param playlist
	 */
	private static void addSongs(Map<String, Playlist> idtoPlayLists, Map<String, Song> idtoSongs, Playlist playlist) {
		Set<String> revisedSongIds = new HashSet<String>(idtoPlayLists.get(playlist.getId()).getSongIds());
		for (String songId : playlist.getSongIds()) {
			if (idtoSongs.containsKey(songId)) {
				revisedSongIds.add(songId);
			}
		}
		Playlist mixtape_playlist = idtoPlayLists.get(playlist.getId());
		mixtape_playlist.setSongIds(new ArrayList<String>(revisedSongIds));
		idtoPlayLists.put(playlist.getId(), mixtape_playlist);
	}

}
