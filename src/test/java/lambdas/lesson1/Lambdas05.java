package lambdas.lesson1;

import org.junit.Test;

import java.util.function.Function;

public class Lambdas05 {
    private <T> void printResult(T t, Function<T, String> f) {
        System.out.println(f.apply(t));
    }

    private final Person person = new Person("John", "Galt");

    @Test
    public void printField() {
        printResult(person, Person::getLastName);
    }


    private static class PersonHelper {
        public static String stringRepresentation(Person person) {
            return person.toString();
        }
    }


    @Test
    public void printStringRepresentation() {
        printResult(person, PersonHelper::stringRepresentation);
    }

    @Test
    public void exception() {
        Runnable r = () -> {
            //Thread.sleep(100);
            person.print();
        };

        r.run();
    }

    private interface DoSmth {
        void doSmth();
    }

    private void conflict(Runnable r) {
        System.out.println("Runnable");
        r.run();
    }

    private void conflict(DoSmth d) {
        System.out.println("Runnable");
        d.doSmth();
    }

    private String printAndReturn() {
        person.print();
        return person.toString();
    }

    @Test
    public void callConflict() {
        final DoSmth printAndReturn = this::printAndReturn;
        conflict(printAndReturn);
    }

    private interface PersonFactory {
        Person create(String name, String lastName);
    }

    private void withFactory(PersonFactory pf) {
        pf.create("name", "lastName").print();
    }

    @Test
    public void factory() {
        withFactory(Person::new);
    }
}
