package bigdata;

import java.util.Objects;

public class Equals {
    private int passport;

    public Equals(int passport) {
        this.passport = passport;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equals equals1 = (Equals) o;
        return passport == equals1.passport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

}
