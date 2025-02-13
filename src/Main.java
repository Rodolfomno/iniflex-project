import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static List<Worker> workers = new ArrayList<>();
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
}