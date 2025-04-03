import java.util.OptionalInt;

public class PersonBuilder {

    private String name;
    private String surname;
    private OptionalInt age;
    private String address;

    public PersonBuilder(){}

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }
    public PersonBuilder setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
        this.age = OptionalInt.of(age);
        return this;
    }
    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        if (name != null || surname != null) {
            Person person;
            if (age.isPresent()) {
                person = new Person(name, surname, age.getAsInt());
                person.setAddress(address);
                return person;
            }
            person = new Person(name, surname);
            person.setAddress(address);
            return person;
        } else {
            throw new IllegalStateException("Failed to create a Person, required name or surname fields are not specified");
        }
    }
}
