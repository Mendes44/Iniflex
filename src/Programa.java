import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		//Lista para inserir todos funcionarios
		List<Funcionarios> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionarios("Joao", LocalDate.of(1990, 1, 10), new BigDecimal(5000), "Analista de TI"));
		funcionarios.add(new Funcionarios("Maria", LocalDate.of(1995, 2, 15), new BigDecimal(5000), "Analista de Dados"));
		funcionarios.add(new Funcionarios("Calors", LocalDate.of(1997, 3, 20), new BigDecimal(7000), "Gerente de TI"));
		
		//Remove o funcionario JOAO
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("Joao"));
		
		
		//Imprimir Todos os Funciinarios
		System.out.println(":::Lista de Funcionarios:::");
		funcionarios.forEach(funcionario -> System.out.println(
				"Nome: " + funcionario.getNome() + 
				", Data de Nascimento: " + funcionario.getDataNascimento().
				format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
				", Salario: " + funcionario.getSalario().setScale(2, BigDecimal.ROUND_HALF_UP) + 
				", Função: " + funcionario.getFuncao()) );
		
		
		
		//Aumentar Salario em 10%
		funcionarios.forEach(funcionario -> funcionario.getSalario().multiply(new BigDecimal("1.10")));
		
		//Agrupar por função
		Map<String, List<Funcionarios>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionarios::getFuncao));
		
		
		
		
		sc.close();

	}

}
