package model;

public record CreateEmployee(String firstName, String lastName, String middleName, int companyId, String email,
                             String url, String phone, boolean isActive,
                             String token) {
}
