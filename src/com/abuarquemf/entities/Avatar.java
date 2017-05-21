package com.abuarquemf.entities;


import java.util.ArrayList;
import java.util.List;

public class Avatar {
    private String name;
    private List<Emotion> emotions;

    @Override
    public String toString() {
        return "Avatar{" +
                "name='" + name + '\'' +
                ", emotions=" + emotions +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avatar(String name, List<Emotion> emotions) {
        this.name = name;
        this.emotions = new ArrayList<>(emotions);
    }

    public Avatar() {
        this("", new ArrayList<Emotion>());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Avatar) {
            Avatar avatar = (Avatar) obj;
            return name.equals(avatar.name);
        }
        return false;
    }


    public void addEmotion(Emotion emotion) {
        emotions.add(emotion);
    }

    public void setEmotions(List<Emotion> emotions) {
        this.emotions.clear();
        this.emotions.addAll(emotions);
    }

    public List<Emotion> getEmotions() {
        return emotions;
    }

    public Emotion findEmotion(String emotionName) {
        for (Emotion emotion : emotions) {
            if(emotion.getEmotionType().equals(emotionName))
                return emotion;
        }
        return null;
    }
}

