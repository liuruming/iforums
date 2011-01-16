package net.iforums.beans;

import java.io.Serializable;

import net.iforums.utils.JsonUtil;

public class KeyValue<K,V> implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6728940066447918575L;
    private K key;
    private V value;
    public KeyValue(K key,V value){
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    
    public String toString(){
        return JsonUtil.toString(this);
    }
}
