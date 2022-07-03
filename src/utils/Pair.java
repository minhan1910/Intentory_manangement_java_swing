package utils;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {
	private static final long serialVersionUID = 1L;
	private K primaryValue;
	private V secondaryValue;

	public K getPrimaryValue() {
		return primaryValue;
	}

	public void setPrimaryValue(K primaryValue) {
		this.primaryValue = primaryValue;
	}

	public V getSecondaryValue() {
		return secondaryValue;
	}

	public void setSecondaryValue(V secondaryValue) {
		this.secondaryValue = secondaryValue;
	}

}
