package fr.jehann.app.dto;

import fr.jehann.app.enums.MemberRole;

public class LoginResponseDTO {

    private long id;
    private MemberRole role;

   // private String Token;


    public LoginResponseDTO() {
    }

    public LoginResponseDTO(long id, MemberRole role) {
        this.id = id;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
