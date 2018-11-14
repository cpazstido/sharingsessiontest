package com.cf.sessiontest.optional;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionalTest {
    public static void main(String[] args) {
        Country country = null;
        country = new Country();
//        country.setIsoCode("asdf");

        Address address = new Address();
        address.setCountry(country);

//        address = null;

        User user = new User();
        user.setAddress(address);

        String isoCode = Optional.ofNullable(user)
                .map(u->u.getAddress())
                .map(a->a.getCountry())
                .map(c->c.getIsoCode())
//                .get()
                .orElse("default")
                ;
        System.out.println(isoCode);

//        Random seed = new Random();
//        Supplier<Integer> random = seed::nextInt;
//        System.out.println(random.get());
//        Stream.generate(random).limit(10).forEach(System.out::println);

        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);

    }


}
