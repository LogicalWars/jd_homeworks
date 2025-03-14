public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);
//        Может произойти деление на ноль. Делаем проверку, если знаменатель
//        не равен 0, то выполняем деление, иначе возвращаем 0
        int c = calc.isPositive.test(b) ? calc.devide.apply(a, b) : 0;
        calc.println.accept(c);
    }
}