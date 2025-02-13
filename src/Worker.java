import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Worker extends Person {
    private static final BigDecimal MIN_SALARY = BigDecimal.valueOf(1212);
    private BigDecimal salary;
    private String function;


    public Worker(){
    }
    public Worker(String name, String birthDate, BigDecimal salary, String function) {
        super(name, birthDate);
        this.salary = salary;
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String BigDecimalFormat(BigDecimal value){
        DecimalFormat formater = new DecimalFormat("#,###.00");
        return formater.format(value);
    }

    public long ageCalc(){
        LocalDate birthDate = super.convertStrintToLocalDate(getBirthDate());
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public double calcularQuantidadeSalariosMinimos(){
        return this.getSalary().divide(MIN_SALARY, 2).doubleValue();

    }

    @Override
    public String toString() {
        return  "nome: " + super.getName() +
                ", data de nascimento: " + super.getBirthDate() +
                ", salário: R$" + this.BigDecimalFormat(salary) +
                ", função: " + function;
    }
}
