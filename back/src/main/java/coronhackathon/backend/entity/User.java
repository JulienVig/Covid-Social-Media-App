package coronhackathon.backend.entity;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private long pwdHash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(long pwdHash) {
        this.pwdHash = pwdHash;
    }
}
