import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static List<Worker> workers = new ArrayList<>();
    public static Map<String, List<Worker>> workersByFunctionMap = new TreeMap<>();
    public static String[][] workersData = {
            {"Maria","18/10/2000","2009.44","Operador"},
            {"João","12/05/1990","2284.38","Operador"},
            {"Caio","02/05/1961","9836.14","Coordenador"},
            {"Miguel","14/10/1988","19119.88","Diretor"},
            {"Alice","05/01/1995","2234.68","Recepcionista"},
            {"Heitor","19/11/1999","1582.72","Operador"},
            {"Arthur","31/03/1993","4071.84","Contador"},
            {"Laura","08/07/1994","3017.45","Gerente"},
            {"Heloísa","24/05/2003","1606.85","Eletricista"},
            {"Helena","02/09/1996","2799.93","Gerente"}
    };

    public static void main(String[] args) {

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

        for(String[] workersData: workersData){
            Worker worker = new Worker(workersData[0],workersData[1],new BigDecimal(workersData[2]),workersData[3]);
            workers.add(worker);
        }
        System.out.println("Lista de funcionários: \n");
        printWorkers();

        //3.2 – Remover o funcionário “João” da lista.

        removeWorkerByName("João");

        //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        //• informação de data deve ser exibido no formato dd/mm/aaaa;
        //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.

        System.out.println("\nLista de Funcionários após remoção de funcionário 'João':\n");
        printWorkers();


        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        raiseWorkerSalaryByPercentage(10.0);
        System.out.println("\nFuncionários com reajuste de 10% no salário: \n");
        printWorkers();

        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        groupWorkersByFunctionMap();

        //3.6 - Imprimir os funcionários, agrupados por função.

        System.out.println("\nFuncionários agrupados por função: \n");
        printWorkerByFunction();

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        printWorkersBirthdayBy10and12();

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        Worker funcionarioMaiorIdade = funcionarioMaiorIdade();
        System.out.println("\nFuncionário com maior idade: \n");
        System.out.println("Nome: " + funcionarioMaiorIdade.getName() + ", idade: " + funcionarioMaiorIdade.ageCalc() + " anos.");

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.

        System.out.println("\nLista de funcionários em ordem alfabética: \n");
        workersByName();
        printWorkers();

        //3.11 – Imprimir o total dos salários dos funcionários.

        System.out.println("\nO valor total do salários dos funcionários é de: R$" + bigDecimalFormater(wokersTotalSalary()));

        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

        System.out.println("\nLista de funcionários e quantidade de salários mínimos: \n");

        printTotalOfMinSalaryOfWorkers();
    }

    public static void printWorkers(){
        workers.forEach(System.out::println);
    }

    public static void removeWorkerByName(String name){
        workers.removeIf(worker -> worker.getName().equalsIgnoreCase(name));
    }

    public static void raiseWorkerSalaryByPercentage(Double percentage){
        workers.forEach(worker -> {
            var actualSalary = worker.getSalary();
            worker.setSalary(actualSalary.multiply(new BigDecimal(1 + percentage/100)));
        });
    }

    public static void groupWorkersByFunctionMap(){
        for(Worker worker: workers){
            String function = worker.getFunction();
            if(!workersByFunctionMap.containsKey(function)){
                workersByFunctionMap.put(function, new ArrayList<>());
            }
            workersByFunctionMap.get(function).add(worker);
        }
    }

    public static void printWorkerByFunction(){
        for (Map.Entry<String, List<Worker>> entry: workersByFunctionMap.entrySet()){
            System.out.println("Função: " + entry.getKey());
            for (Worker worker: entry.getValue()){
                System.out.println("        " + worker);
            }
        }
    }

    public static void printWorkersBirthdayBy10and12(){
        List<Worker> birthdays = new ArrayList<>();
        for(Worker worker: workers){
            Month birthDayMonth = LocalDate.parse(worker.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).getMonth();
            if(birthDayMonth.equals(Month.OCTOBER) || birthDayMonth.equals(Month.DECEMBER)){
                birthdays.add(worker);
            }
        }
        System.out.println("\nAniversariantes dos Meses 10 e 12: \n");
        birthdays.forEach(System.out::println);
    }

    public static Worker funcionarioMaiorIdade(){
        return workers.stream().max(Comparator.comparing(Worker::ageCalc)).get();
    }

    public static void workersByName (){
        workers.sort(Comparator.comparing(Person::getName));
    }

    public static BigDecimal wokersTotalSalary(){
        BigDecimal total = BigDecimal.ZERO;
        for(Worker worker: workers){
            total = total.add(worker.getSalary());
        }
        return total;
    }

    public static String bigDecimalFormater(BigDecimal value){
        DecimalFormat formater = new DecimalFormat("#,###.00");
        return formater.format(value);
    }

    public static void printTotalOfMinSalaryOfWorkers(){
        for (Worker worker: workers){
            System.out.printf("Nome: %s , Salários Mínimos: %.1f\n", worker.getName(), worker.calcMinSalary());
        }
    }

}