package coronhackathon.backend.service;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(String m){
        super(m);
    }
}
