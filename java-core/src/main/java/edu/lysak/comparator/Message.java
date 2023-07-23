package edu.lysak.comparator;

import java.time.LocalDate;

class Message {

    private final String from;
    private final String content;
    private final LocalDate created;
    private int likes;

    public Message(String from, String content, int likes, String created) {
        this.from = from;
        this.content = content;
        this.likes = likes;
        this.created = LocalDate.parse(created);
    }

    public String getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreated() {
        return created;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return created.toString() + " " + from + " wrote: " +
                content + " (" + likes + ")";
    }
}
