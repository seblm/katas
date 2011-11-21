package fr.free.lemerdy;

import static fr.free.lemerdy.FooBarQix.contains3;
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
    public void shouldDetect3IsContained() throws Exception {
        assertThat(contains3(3)).isTrue();
        assertThat(contains3(1)).isFalse();
        assertThat(contains3(83)).isTrue();
        assertThat(contains3(-29)).isFalse();
        
        assertThat(FooBarQix.contains5(0)).isFalse();
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
    }
    
}
