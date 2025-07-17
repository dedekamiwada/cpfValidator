import java.util.Scanner;

public class cpfValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o CPF (apenas números):");
        String cpf = scanner.nextLine();
        if (isValidCPF(cpf)) {
            System.out.println("CPF válido.");
        } else {
            System.out.println("CPF inválido.");
        }
        scanner.close(); 
    }

    public static boolean isValidCPF(String cpf) {
        // Verifica se o CPF é nulo, tem o tamanho incorreto ou contém caracteres não numéricos
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        // Verifica CPFs com todos os dígitos iguais (que são inválidos)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        // Calcula o primeiro dígito verificador (d1)
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }
        int remainder = sum % 11;
        int d1 = (remainder < 2) ? 0 : (11 - remainder);

        // Calcula o segundo dígito verificador (d2)
        sum = 0; 
        for (int i = 1; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }
        remainder = sum % 11;
        int d2 = (remainder < 2) ? 0 : (11 - remainder);

        // Compara os dígitos verificadores calculados com os dígitos do CPF
        if (d1 == digits[9] && d2 == digits[10]) {
            return true;
        } else {
            return false;
        }
    }
}