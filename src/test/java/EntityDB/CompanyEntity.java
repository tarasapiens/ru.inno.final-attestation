package EntityDB;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "company", schema = "public", catalog = "x_clients_db_3fmx")
public class CompanyEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "create_timestamp")
    private String createTimestamp;

    @Column(name = "change_timestamp")
    private String changeTimestamp;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(name = "deleted_at")
    private String deletedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(String changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createTimestamp='" + createTimestamp + '\'' +
                ", changeTimestamp='" + changeTimestamp + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deletedAt='" + deletedAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyEntity that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && Objects.equals(getCreateTimestamp(), that.getCreateTimestamp()) && Objects.equals(getChangeTimestamp(), that.getChangeTimestamp()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDeletedAt(), that.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getCreateTimestamp(), getChangeTimestamp(), getName(), getDescription(), getDeletedAt());
    }
}
