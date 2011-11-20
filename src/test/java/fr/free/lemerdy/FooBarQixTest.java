package fr.free.lemerdy;

import static org.fest.assertions.Assertions.assertThat;
import static fr.free.lemerdy.FooBarQix.is3Dividable;
import static fr.free.lemerdy.FooBarQix.contains3;
import static fr.free.lemerdy.FooBarQix.fooBarQix;

import org.junit.Test;

public class FooBarQixTest {

    @Test
    public void shouldDetect3Divider() throws Exception {
        assertThat(is3Dividable(3)).isTrue();
        assertThat(is3Dividable(-3)).isTrue();
        assertThat(is3Dividable(0)).isTrue();
        assertThat(is3Dividable(1)).isFalse();
    }
    
    @Test
    public void shouldDetect3IsContained() throws Exception {
        assertThat(contains3(3)).isTrue();
        assertThat(contains3(1)).isFalse();
        assertThat(contains3(83)).isTrue();
        assertThat(contains3(-29)).isFalse();
    }
    
    @Test
    public void shoudFooBarQixANumber() throws Exception {
        assertThat(fooBarQix(1)).isEqualTo("1");
        assertThat(fooBarQix(2)).isEqualTo("2");
        assertThat(fooBarQix(3)).isEqualTo("FooFoo");
    }
    
}
