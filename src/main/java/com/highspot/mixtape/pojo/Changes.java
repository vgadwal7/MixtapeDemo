
package com.highspot.mixtape.pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Changes pojo
 * 
 * @author VG
 *
 */
public class Changes implements Serializable {

	@SerializedName("playlists")
	@Expose
	private List<Playlist> playlists = null;
	private final static long serialVersionUID = -3679675230891142153L;

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("playlists", playlists).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(playlists).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Changes) == false) {
			return false;
		}
		Changes rhs = ((Changes) other);
		return new EqualsBuilder().append(playlists, rhs.playlists).isEquals();
	}

}
