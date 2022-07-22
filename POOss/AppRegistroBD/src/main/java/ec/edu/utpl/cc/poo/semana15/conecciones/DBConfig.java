package ec.edu.utpl.cc.poo.semana15.conecciones;

public class DBConfig {
    private String jdbcURL;
    private String username;
    private String pasword;

    public DBConfig(String jdbcURL, String username, String pasword) {
        this.jdbcURL = jdbcURL;
        this.username = username;
        this.pasword = pasword;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPasword() {
        return pasword;
    }
}
