package models.datastructures;

import java.time.LocalDateTime;

/**
 * Data structure for database ranking (table scores)
 */
public record DataScores(LocalDateTime gameTime, String playerName, String word, String missedLetters,
                         int timeSeconds) {

}