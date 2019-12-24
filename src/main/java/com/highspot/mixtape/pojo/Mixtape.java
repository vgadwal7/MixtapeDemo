
package com.highspot.mixtape.pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Mixtape pojo
 * 
 * @author VG
 *
 */
public class Mixtape implements Serializable {

	private static final long serialVersionUID = 1L;
	@SerializedName("users")
	@Expose
	private List<User> users = null;
	@SerializedName("playlists")
	@Expose
	private List<Playlist> playlists = null;
	@SerializedName("songs")
	@Expose
	private List<Song> songs = null;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("users", users).append("playlists", playlists).append("songs", songs)
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(playlists).append(users).append(songs).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Mixtape) == false) {
			return false;
		}
		Mixtape rhs = ((Mixtape) other);
		return new EqualsBuilder().append(playlists, rhs.playlists).append(users, rhs.users).append(songs, rhs.songs)
				.isEquals();
	}

}
