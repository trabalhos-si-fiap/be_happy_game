package happygame.api.models;


import java.time.LocalDate;

public class Player {
    private String name;
    private String mail;
    private String password;
    private LocalDate birhDate;

    public boolean isOfAge() {
        return true;
    }

}
