import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("1. Найти количество несовершеннолетних (т.е. людей младше 18 лет).");
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершоннолетних: "+count);

        System.out.println("\n2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).");
        List<String> family = persons.stream()
                .filter(x -> x.getAge() < 27 && x.getAge() > 18)
                .filter(x -> x.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество фамилий в списке: "+family.size());

        System.out.println("\n3. Получить отсортированный по фамилии список потенциально работоспособных людей с высшим " +
                "образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).");
        List<Person> person = persons.stream()
                .filter(x -> (x.getAge() > 18) && (x.getAge() < 60 && x.getSex().equals(Sex.WOMAN)) || (x.getAge() < 65 && x.getSex().equals(Sex.MAN)))
                .filter(x-> x.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Количество людей в списке: "+person.size());

    }
}