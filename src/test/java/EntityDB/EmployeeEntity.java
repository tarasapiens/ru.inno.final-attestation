package EntityDB;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "employee", schema = "public", catalog = "x_clients_db_3fmx")
public class EmployeeEntity {

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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "birthdate")
    private String birthDate;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "company_id")
    private String companyId;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createTimestamp='" + createTimestamp + '\'' +
                ", changeTimestamp='" + changeTimestamp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && Objects.equals(getCreateTimestamp(), that.getCreateTimestamp()) && Objects.equals(getChangeTimestamp(), that.getChangeTimestamp()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getMiddleName(), that.getMiddleName()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getAvatarUrl(), that.getAvatarUrl()) && Objects.equals(getCompanyId(), that.getCompanyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getCreateTimestamp(), getChangeTimestamp(), getFirstName(), getLastName(), getMiddleName(), getPhone(), getEmail(), getBirthDate(), getAvatarUrl(), getCompanyId());
    }
}
