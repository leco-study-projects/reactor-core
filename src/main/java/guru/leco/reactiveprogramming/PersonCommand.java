package guru.leco.reactiveprogramming;

import lombok.Getter;

@Getter
public class PersonCommand {

    public PersonCommand(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
    }

    private String firstName;

    private String lastName;

    public String sayHello() {
        return String.format("Hello %s %s, how i can help you ?", firstName, lastName);
    }
}
