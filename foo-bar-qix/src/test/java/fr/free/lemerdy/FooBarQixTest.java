package fr.free.lemerdy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooBarQixTest {

    @Test
    public void shouldDetectIsContained() {
        assertThat(new FooBarQixer(-29).substitutedWhenSpecialNumbersOccurs()).isEqualTo("");
        assertThat(new FooBarQixer(-5).substitutedWhenSpecialNumbersOccurs()).isEqualTo("Bar");
        assertThat(new FooBarQixer(0).substitutedWhenSpecialNumbersOccurs()).isEqualTo("");
        assertThat(new FooBarQixer(1).substitutedWhenSpecialNumbersOccurs()).isEqualTo("");
        assertThat(new FooBarQixer(3).substitutedWhenSpecialNumbersOccurs()).isEqualTo("Foo");
        assertThat(new FooBarQixer(5).substitutedWhenSpecialNumbersOccurs()).isEqualTo("Bar");
        assertThat(new FooBarQixer(49).substitutedWhenSpecialNumbersOccurs()).isEqualTo("");
        assertThat(new FooBarQixer(53).substitutedWhenSpecialNumbersOccurs()).isEqualTo("BarFoo");
        assertThat(new FooBarQixer(83).substitutedWhenSpecialNumbersOccurs()).isEqualTo("Foo");
    }

    @Test
    public void shouldFooBarQixANumber() {
        assertThat(new FooBarQixer(1).toString()).isEqualTo("1");
        assertThat(new FooBarQixer(2).toString()).isEqualTo("2");
        assertThat(new FooBarQixer(3).toString()).isEqualTo("FooFoo");
        assertThat(new FooBarQixer(4).toString()).isEqualTo("4");
        assertThat(new FooBarQixer(5).toString()).isEqualTo("BarBar");
        assertThat(new FooBarQixer(6).toString()).isEqualTo("Foo");
        assertThat(new FooBarQixer(7).toString()).isEqualTo("QixQix");
        assertThat(new FooBarQixer(8).toString()).isEqualTo("8");
        assertThat(new FooBarQixer(9).toString()).isEqualTo("Foo");
        assertThat(new FooBarQixer(10).toString()).isEqualTo("Bar");
        assertThat(new FooBarQixer(13).toString()).isEqualTo("Foo");
        assertThat(new FooBarQixer(15).toString()).isEqualTo("FooBarBar");
        assertThat(new FooBarQixer(21).toString()).isEqualTo("FooQix");
        assertThat(new FooBarQixer(33).toString()).isEqualTo("FooFooFoo");
        assertThat(new FooBarQixer(51).toString()).isEqualTo("FooBar");
        assertThat(new FooBarQixer(53).toString()).isEqualTo("BarFoo");
    }

}
