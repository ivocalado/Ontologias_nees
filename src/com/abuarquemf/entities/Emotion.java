package com.abuarquemf.entities;

/**
 * This class represents a emotion
 * @author ivocalado
 *
 */
public class Emotion {
    private EmotionType emotionType;
    private transient String picPath;

    public Emotion(EmotionType emotionType, String picPath) {
        super();
        this.emotionType = emotionType;
        this.picPath = picPath;
    }

    /**
     * Should be called when the emotion type is the same if picPath
     * @param emotionType
     */
    public Emotion(EmotionType emotionType) {
        this(emotionType, emotionType.toString());
    }

    /**
     * @return the emotionType
     */
    public EmotionType getEmotionType() {
        return emotionType;
    }

    /**
     * @param emotionType the emotionType to set
     */
    public void setEmotionType(EmotionType emotionType) {
        this.emotionType = emotionType;
    }

    /**
     * @return the picPath
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * @param picPath the picPath to set
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return emotionType.toString();
    }
}
