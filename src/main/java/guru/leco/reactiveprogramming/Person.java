package guru.leco.reactiveprogramming;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Person {

    private String firstName;

    private String lastName;

    public String sayHello() {
        return String.format("Hello %s %s, how i can help you ?", firstName, lastName);
    }
}
