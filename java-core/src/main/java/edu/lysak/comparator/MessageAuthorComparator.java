package edu.lysak.comparator;

import java.util.Comparator;

public class MessageAuthorComparator implements Comparator<Message> {

    @Override
    public int compare(Message message1, Message message2) {
        return message1.getFrom().compareTo(message2.getFrom());
    }
}
