package guru.leco.reactiveprogramming;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ReactiveProgrammingApplicationTests {

    private Person leandro = Person
            .builder()
            .firstName("Leandro")
            .lastName("Costa")
            .build();

    private Person julina = Person
            .builder()
            .firstName("Juliana")
            .lastName("Costa")
            .build();

    private Person gabriel = Person
            .builder()
            .firstName("Gabriel")
            .lastName("Costa")
            .build();

    @Test
    public void testMono() {

        Mono<Person> mono = Mono.just(this.leandro);

        Person person = mono.block();

        log.info(person.sayHello());
    }

    @Test
    public void testMonoMapping() {

        Mono<Person> mono = Mono.just(this.leandro);

        PersonCommand command = mono
                .map(PersonCommand::new)
                .block();

        log.info(command.sayHello());
    }

    @Test
    public void testFlux() {
        Flux<Person> personFlux = Flux.just(this.leandro, this.gabriel, this.julina);
        personFlux.subscribe(person -> log.info(person.sayHello()));
    }

    @Test
    public void testFluxAddDelay() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> personFlux = Flux.just(this.leandro, this.gabriel, this.julina);
        personFlux
                .delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayHello()));

        countDownLatch.await();
    }
}

