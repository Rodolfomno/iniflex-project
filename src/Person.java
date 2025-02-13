import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String name;
    private LocalDate birthDate;

    public Person() {
    }

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = this.convertStrintToLocalDate(birthDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return this.convertLocalDateToString(birthDate);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = this.convertStrintToLocalDate(birthDate);
    }

    @Override
    public String toString() {
        return "nome='" + name + '\'' +
                ", dataNascimento=" + this.convertLocalDateToString(this.birthDate) + ".";
    }

    public LocalDate convertStrintToLocalDate(String dataString){
        return LocalDate.parse(dataString, DATE_FORMAT);
    }

    public String convertLocalDateToString(LocalDate date){
        return date.format(DATE_FORMAT);
    }
}

