package ro.tuc.ds2020.security;

public class JwtTkn{
    private final String jwtTkn;

    public JwtTkn(String jwtTkn) {
        this.jwtTkn = jwtTkn;
    }

    public String getTkn() {
        return this.jwtTkn;
    }
}
