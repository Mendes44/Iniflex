import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
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
		Map < String, List<Funcionarios >> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionarios::getFuncao));
		
		//Imprimir Funcionarios pela função
		System.out.println("");
		System.out.println("Funcionarios agrupados por função: ");
		funcionariosPorFuncao.forEach((funcao, lista) -> {
			System.out.println(funcao + ":");
			lista.forEach(funcioarios -> System.out.println("- " + funcioarios.getNome()));
		});
		
		//Imprimir Aniversariantes de 10 e 12
		System.out.println();
		System.out.println("Aniversariantes do Mês 10(OUTUBRO) e 12(DEZEMBRO): ");
		funcionarios.stream()
			.filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
			.forEach(funcionario -> System.out.println(funcionario.getNome()));
		
		
		//Imprimindo Funcionario Mais Velho.
		Funcionarios maisVelho = Collections.max(funcionarios, Comparator.comparing(funcionario -> ChronoUnit.YEARS.between(
				funcionario.getDataNascimento(), LocalDate.now())));
		System.out.println();
		System.out.println("Funcionario mais Velho: " + maisVelho.getNome() + 
				", Idade: " + ChronoUnit.YEARS.between(maisVelho.getDataNascimento(), LocalDate.now()));
		
		//Imprimindo em Ordem Alfabetica
		System.out.println("\n Lista de Funcionarios em  ORDEM ALFABETICA: ");
		funcionarios.stream()
			.map(Funcionarios::getNome)
			.sorted()
			.forEach(System.out::println);
		
		//Imprimir total dos salarios dos funcionários
		BigDecimal totalSalarios = funcionarios.stream()
												.map(Funcionarios::getSalario)
												.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("\n Total dos Salarios dos Funcionarios: " + totalSalarios.setScale(2, BigDecimal.ROUND_HALF_UP));
		
		//Imprimir quantos salarios minimos ganha cada funcionario
		BigDecimal salarioMinimo = new BigDecimal("1212.00");
		System.out.println();
		System.out.println("Salarios Minimos ganhos por cada funcionario: ");
		funcionarios.forEach(funcionario -> {
			BigDecimal salarioMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
			System.out.println(funcionario.getNome() + ": " + salarioMinimos);
		});
		
		
		sc.close();
	}
}
