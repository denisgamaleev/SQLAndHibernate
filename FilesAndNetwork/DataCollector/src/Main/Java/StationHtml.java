import java.util.Objects;

public class StationHtml {
    String number;
    String line;
    String name;
    boolean connection;

    public StationHtml(String number, String name) {
        this.number = number;
        this.name = name;
    }


    public StationHtml(String number, String line, String name, boolean connection) {
        this.number = number;
        this.line = line;
        this.name = name;
        this.connection = connection;

    }
    public String getNumber() {
        return number;
    }

    public String getLine() {
        return line;
    }

    public String getName() {
        return name;
    }
    public boolean getConnection() {
        return connection;
    }
    @Override
    public String toString() {
        return number + ":" +
                line + ":" +
                name + ":" +
                connection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationHtml that)) return false;
        return getConnection() == that.getConnection() && Objects.equals(getLine(), that.getLine()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLine(), getName(), getConnection());
    }
}
