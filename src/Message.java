import java.sql.Array;
import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private long id;
    private String content;
    private String author;
    private LocalDateTime timestamp;
    private List<String> tags;
    private MicroblogDatabase database;
    private long replytoId;
    private boolean republished;

    public Message(long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Array getTags() {
        return (Array) tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getReplyToId() {
        return replytoId;
    }
    public boolean isRepublished() {
        return republished;
    }

    public void setRepublished(boolean republished) {
        this.republished = republished;
    }
}

