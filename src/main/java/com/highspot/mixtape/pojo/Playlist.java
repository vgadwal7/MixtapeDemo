
package com.highspot.mixtape.pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Playlist pojo
 * 
 * @author VG
 *
 */
public class Playlist implements Serializable {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("user_id")
	@Expose
	private String userId;
	@SerializedName("action")
	@Expose
	private String action;
	@SerializedName("song_ids")
	@Expose
	private List<String> songIds = null;
	private final static long serialVersionUID = 1165176336041033776L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<String> getSongIds() {
		return songIds;
	}

	public void setSongIds(List<String> songIds) {
		this.songIds = songIds;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("userId", userId).append("action", action)
				.append("songIds", songIds).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(action).append(id).append(userId).append(songIds).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Playlist) == false) {
			return false;
		}
		Playlist rhs = ((Playlist) other);
		return new EqualsBuilder().append(action, rhs.action).append(id, rhs.id).append(userId, rhs.userId)
				.append(songIds, rhs.songIds).isEquals();
	}

}
