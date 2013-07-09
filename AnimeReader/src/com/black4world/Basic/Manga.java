package com.black4world.Basic;

/**
 * Created with IntelliJ IDEA.
 * User: black4ninja
 * Date: 8/07/13
 * Time: 04:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Manga implements Comparable<Manga>{
    private int image;
    private String title;
    private String description;
    private String id;
    private String extra1;
    private int extra2;

    public Manga(int i, String t, String d, String id){
        this.setImage(i);
        this.setTitle(t);
        this.setDescription(d);
        this.setId(id);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public int getExtra2() {
        return extra2;
    }

    public void setExtra2(int extra2) {
        this.extra2 = extra2;
    }

    @Override
    public int compareTo(Manga contacto) {
        return this.getTitle().compareTo(contacto.getTitle());
    }
}


