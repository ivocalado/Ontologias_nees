package com.abuarquemf.entities;

import java.util.*;

public class AvatarCollection {
    //private Collection<Avatar> avatars = new ArrayList<>();
    private Map<String, Avatar> avatars = new HashMap<>();

    public Collection<Avatar> listAvatars() {
        return new ArrayList<>(this.avatars.values());
    }

    public void addAvatar(Avatar avatar) {
        this.avatars.put(avatar.getName(), avatar);
    }

    public Avatar getAvatarByName(String name) {
        return this.avatars.get(name);
    }

    public AvatarCollection() {
        List<Emotion> l1 = new ArrayList<>();
        avatars.put("Adriana Marangoni", new Avatar("Adriana Marangoni",l1));

        List<Emotion> l2 = new ArrayList<>();
        avatars.put("Anna Costa", new Avatar("Anna Costa",l2));
    }
}
