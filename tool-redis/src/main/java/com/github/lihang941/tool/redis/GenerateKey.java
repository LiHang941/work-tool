package com.github.lihang941.tool.redis;

/**
 * Redis Key
 *
 * @author : lihang1329@gmail.com
 */
public class GenerateKey {


    public static String DEFAULT_KEY_HEAD = "default:generate:key";
    public static String DEFAULT_SPLICE = ":";

    private String head = null;
    private String splice = null;

    public GenerateKey() {

    }

    public GenerateKey(String head) {
        this.head = head;
    }

    public GenerateKey(String head, String splice) {
        this.head = head;
        this.splice = splice;
    }


    public GenerateKey setHead(String head) {
        this.head = head;
        return this;
    }

    public String getHead() {
        if (head == null)
            return DEFAULT_KEY_HEAD;
        return head;
    }


    public GenerateKey setSplice(String splice) {
        this.splice = splice;
        return this;
    }

    private String getSplice() {
        if (splice == null)
            return DEFAULT_SPLICE;
        return splice;
    }


    public String generate(String... keys) {
        return generateKey(getHead(), getSplice(), keys);
    }


    public String generateCommon(String keyHead, String... keys) {
        return generateKey(keyHead, getSplice(), keys);
    }


    public String generateAll(String keyHead, String splice, String... keys) {
        return generateKey(keyHead, splice, keys);
    }


    public static String generateKey(String keyHead, String splice, String... keys) {
        return keyHead + String.join(splice, keys);
    }

}
