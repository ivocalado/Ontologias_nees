package com.abuarquemf.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class AvatarHandler {
	private List<Avatar> avatars = new ArrayList<>();

	@XmlElement
	public List<Avatar> getAvatars() {
		return avatars;
	}

	public void setAvatars(List<Avatar> avatars) {
		this.avatars = avatars;
	}

	public AvatarHandler() {

	}

	public AvatarHandler(List<Avatar> avatars) {
		this.avatars = avatars;
	}
}
