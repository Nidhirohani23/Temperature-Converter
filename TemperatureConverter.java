import java.util.Locale;
import java.util.Scanner;

public class TemperatureConverter {

    public static double celsiusToFahrenheit(double c) { return (c * 9.0 / 5.0) + 32.0; }
    public static double celsiusToKelvin(double c)      { return c + 273.15; }
    public static double fahrenheitToCelsius(double f)  { return (f - 32.0) * 5.0 / 9.0; }
    public static double kelvinToCelsius(double k)      { return k - 273.15; }

    private static char parseUnit(String in) {
        if (in == null) return 0;
        in = in.trim().toLowerCase(Locale.ROOT);
        if (in.isEmpty()) return 0;
        if (in.startsWith("c")) return 'C';
        if (in.startsWith("f")) return 'F';
        if (in.startsWith("k")) return 'K';
        return 0; // invalid
    }

    private static void printResults(double c, double f, double k) {
        System.out.printf("Celsius    : %.2f °C%n", c);
        System.out.printf("Fahrenheit : %.2f °F%n", f);
        System.out.printf("Kelvin     : %.2f K%n", k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); 

        while (true) {
            System.out.print("Enter temperature value (or 'q' to quit): ");
            if (!sc.hasNextDouble()) {
                String token = sc.next().trim();
                if (token.equalsIgnoreCase("q") || token.equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                System.out.println("❌ Please enter a valid number like 25, -12.5, or 300.0");
                continue;
            }
            double value = sc.nextDouble();

            System.out.print("Enter unit (C/Celsius, F/Fahrenheit, K/Kelvin): ");
            String unitStr = sc.next();
            char unit = parseUnit(unitStr);

            if (unit == 0) {
                System.out.println("❌ Invalid unit. Use C, F, or K (e.g., C or Celsius).");
                continue;
            }

            double c, f, k;
            switch (unit) {
                case 'C':
                    c = value;
                    f = celsiusToFahrenheit(c);
                    k = celsiusToKelvin(c);
                    break;
                case 'F':
                    c = fahrenheitToCelsius(value);
                    f = value;
                    k = celsiusToKelvin(c);
                    break;
                default: // 'K'
                    if (value < 0) {
                        System.out.println("❌ Kelvin cannot be negative. Try again.");
                        continue;
                    }
                    c = kelvinToCelsius(value);
                    f = celsiusToFahrenheit(c);
                    k = value;
            }

            if (k < 0) {
                System.out.println("⚠️ Note: resulting Kelvin is negative, which isn’t physically valid.");
            }

            System.out.println("\n--- Converted ---");
            printResults(c, f, k);
            System.out.println();
        }

        sc.close();
    }
}

