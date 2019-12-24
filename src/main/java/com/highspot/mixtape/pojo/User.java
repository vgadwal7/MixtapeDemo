
package com.highspot.mixtape.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * User pojo
 * 
 * @author VG
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1781536165616717010L;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(id).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof User) == false) {
			return false;
		}
		User rhs = ((User) other);
		return new EqualsBuilder().append(name, rhs.name).append(id, rhs.id).isEquals();
	}

}
