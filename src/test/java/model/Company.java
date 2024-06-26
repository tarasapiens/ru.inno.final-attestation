package model;

public record Company(int id, boolean isActive, String createDateTime, String lastChangedDateTime, String name,
                      String description, String deletedAt) {
}
