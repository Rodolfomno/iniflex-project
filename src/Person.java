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
        this.birthDate = this.converterStringParaLocalDate(birthDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBornDate() {
        return this.convertDateToString(birthDate);
    }

    public void setDataNascimento(String birthDate) {
        this.birthDate = this.converterStringParaLocalDate(birthDate);
    }

    @Override
    public String toString() {
        return "nome='" + name + '\'' +
                ", dataNascimento=" + this.convertDateToString(this.birthDate) + ".";
    }

    public LocalDate converterStringParaLocalDate(String dataString){
        return LocalDate.parse(dataString, DATE_FORMAT);
    }

    public String convertDateToString(LocalDate data){
        return data.format(DATE_FORMAT);
    }
}

