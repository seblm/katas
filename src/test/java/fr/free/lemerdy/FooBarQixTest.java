package fr.free.lemerdy;

import static fr.free.lemerdy.FooBarQix.contains;
import static fr.free.lemerdy.FooBarQix.fooBarQix;
import static fr.free.lemerdy.FooBarQix.isDividableBy;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class FooBarQixTest {

    @Test
    public void shouldDetectDividers() throws Exception {
        assertThat(isDividableBy(3, 3)).isTrue();
        assertThat(isDividableBy(-3, 3)).isTrue();
        assertThat(isDividableBy(0, 3)).isTrue();
        assertThat(isDividableBy(1, 3)).isFalse();

        assertThat(isDividableBy(0, 5)).isTrue();
        assertThat(isDividableBy(1, 5)).isFalse();
        assertThat(isDividableBy(5, 5)).isTrue();
        assertThat(isDividableBy(-5, 5)).isTrue();
    }
    
    @Test
    public void shouldDetectIsContained() throws Exception {
        assertThat(contains(-29, 3)).isFalse();
        assertThat(contains(-5, 5)).isTrue();
        assertThat(contains(0, 3)).isFalse();
        assertThat(contains(1, 3)).isFalse();
        assertThat(contains(3, 3)).isTrue();
        assertThat(contains(5, 5)).isTrue();
        assertThat(contains(49, 5)).isFalse();
        assertThat(contains(83, 3)).isTrue();
    }
    
    @Test
    public void shoudFooBarQixANumber() throws Exception {
        assertThat(fooBarQix(1)).isEqualTo("1");
        assertThat(fooBarQix(2)).isEqualTo("2");
        assertThat(fooBarQix(3)).isEqualTo("FooFoo");
        assertThat(fooBarQix(4)).isEqualTo("4");
        assertThat(fooBarQix(5)).isEqualTo("BarBar");
        assertThat(fooBarQix(6)).isEqualTo("Foo");
        assertThat(fooBarQix(7)).isEqualTo("QixQix");
        assertThat(fooBarQix(8)).isEqualTo("8");
        assertThat(fooBarQix(9)).isEqualTo("Foo");
        assertThat(fooBarQix(10)).isEqualTo("Bar");
        assertThat(fooBarQix(51)).isEqualTo("FooBar");
        assertThat(fooBarQix(53)).isEqualTo("BarFoo");
    }
    
}
