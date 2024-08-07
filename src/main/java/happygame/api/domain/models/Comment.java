package happygame.api.domain.models;

import java.time.LocalDateTime;

public class Comment {
    private Player player;
    private String comment;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
