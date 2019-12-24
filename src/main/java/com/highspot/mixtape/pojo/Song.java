
package com.highspot.mixtape.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Song pojo
 * 
 * @author VG
 *
 */
public class Song implements Serializable {

	private static final long serialVersionUID = -5786855993895597327L;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("artist")
	@Expose
	private String artist;
	@SerializedName("title")
	@Expose
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("artist", artist).append("title", title).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(title).append(artist).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Song) == false) {
			return false;
		}
		Song rhs = ((Song) other);
		return new EqualsBuilder().append(id, rhs.id).append(title, rhs.title).append(artist, rhs.artist).isEquals();
	}

}
