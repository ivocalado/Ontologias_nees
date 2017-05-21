package com.abuarquemf.entities;

/**
 * Defines the current allowable emotions. For now, we have statically defined
 * it. We may consider dynamically create new emotions in the future
 * 
 * 
 */
public enum EmotionType {
	OPENED("opened"),
	SADNESS("sadness"),
	SURPRISE("surprise"),
	ANGER("anger"),
	CLOSED("closed"),
	DISGUST("disgust"),
	FEAR("fear"),
	JOY("joy"),
	NEUTRAL("neutral");

	private final String text;

	/**
	 * @param text
	 */
	private EmotionType(final String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}

	public boolean equals(String emotionName) {
		return text.equals(emotionName);
	}

}