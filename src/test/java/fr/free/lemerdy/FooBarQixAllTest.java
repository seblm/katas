package fr.free.lemerdy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooBarQixAllTest {

    private static final FooBarQixToTest fooBarQixToTest = i -> new FooBarQixer(i).toString();

    @Test
    public void test001() {
        assertThat(fooBarQixToTest.fooBarQix(1)).isEqualTo("1");
    }

    @Test
    public void test002() {
        assertThat(fooBarQixToTest.fooBarQix(2)).isEqualTo("2");
    }

    @Test
    public void test003() {
        assertThat(fooBarQixToTest.fooBarQix(3)).isEqualTo("FooFoo");
    }

    @Test
    public void test004() {
        assertThat(fooBarQixToTest.fooBarQix(4)).isEqualTo("4");
    }

    @Test
    public void test005() {
        assertThat(fooBarQixToTest.fooBarQix(5)).isEqualTo("BarBar");
    }

    @Test
    public void test006() {
        assertThat(fooBarQixToTest.fooBarQix(6)).isEqualTo("Foo");
    }

    private interface FooBarQixToTest {
        String fooBarQix(Integer i);
    }

    @Test
    public void test007() {
        assertThat(fooBarQixToTest.fooBarQix(7)).isEqualTo("QixQix");
    }

    @Test
    public void test008() {
        assertThat(fooBarQixToTest.fooBarQix(8)).isEqualTo("8");
    }

    @Test
    public void test009() {
        assertThat(fooBarQixToTest.fooBarQix(9)).isEqualTo("Foo");
    }

    @Test
    public void test010() {
        assertThat(fooBarQixToTest.fooBarQix(10)).isEqualTo("Bar");
    }

    @Test
    public void test011() {
        assertThat(fooBarQixToTest.fooBarQix(11)).isEqualTo("11");
    }

    @Test
    public void test012() {
        assertThat(fooBarQixToTest.fooBarQix(12)).isEqualTo("Foo");
    }

    @Test
    public void test013() {
        assertThat(fooBarQixToTest.fooBarQix(13)).isEqualTo("Foo");
    }

    @Test
    public void test014() {
        assertThat(fooBarQixToTest.fooBarQix(14)).isEqualTo("Qix");
    }

    @Test
    public void test015() {
        assertThat(fooBarQixToTest.fooBarQix(15)).isEqualTo("FooBarBar");
    }

    @Test
    public void test016() {
        assertThat(fooBarQixToTest.fooBarQix(16)).isEqualTo("16");
    }

    @Test
    public void test017() {
        assertThat(fooBarQixToTest.fooBarQix(17)).isEqualTo("Qix");
    }

    @Test
    public void test018() {
        assertThat(fooBarQixToTest.fooBarQix(18)).isEqualTo("Foo");
    }

    @Test
    public void test019() {
        assertThat(fooBarQixToTest.fooBarQix(19)).isEqualTo("19");
    }

    @Test
    public void test020() {
        assertThat(fooBarQixToTest.fooBarQix(20)).isEqualTo("Bar");
    }

    @Test
    public void test021() {
        assertThat(fooBarQixToTest.fooBarQix(21)).isEqualTo("FooQix");
    }

    @Test
    public void test022() {
        assertThat(fooBarQixToTest.fooBarQix(22)).isEqualTo("22");
    }

    @Test
    public void test023() {
        assertThat(fooBarQixToTest.fooBarQix(23)).isEqualTo("Foo");
    }

    @Test
    public void test024() {
        assertThat(fooBarQixToTest.fooBarQix(24)).isEqualTo("Foo");
    }

    @Test
    public void test025() {
        assertThat(fooBarQixToTest.fooBarQix(25)).isEqualTo("BarBar");
    }

    @Test
    public void test026() {
        assertThat(fooBarQixToTest.fooBarQix(26)).isEqualTo("26");
    }

    @Test
    public void test027() {
        assertThat(fooBarQixToTest.fooBarQix(27)).isEqualTo("FooQix");
    }

    @Test
    public void test028() {
        assertThat(fooBarQixToTest.fooBarQix(28)).isEqualTo("Qix");
    }

    @Test
    public void test029() {
        assertThat(fooBarQixToTest.fooBarQix(29)).isEqualTo("29");
    }

    @Test
    public void test030() {
        assertThat(fooBarQixToTest.fooBarQix(30)).isEqualTo("FooBarFoo");
    }

    @Test
    public void test031() {
        assertThat(fooBarQixToTest.fooBarQix(31)).isEqualTo("Foo");
    }

    @Test
    public void test032() {
        assertThat(fooBarQixToTest.fooBarQix(32)).isEqualTo("Foo");
    }

    @Test
    public void test033() {
        assertThat(fooBarQixToTest.fooBarQix(33)).isEqualTo("FooFooFoo");
    }

    @Test
    public void test034() {
        assertThat(fooBarQixToTest.fooBarQix(34)).isEqualTo("Foo");
    }

    @Test
    public void test035() {
        assertThat(fooBarQixToTest.fooBarQix(35)).isEqualTo("BarQixFooBar");
    }

    @Test
    public void test036() {
        assertThat(fooBarQixToTest.fooBarQix(36)).isEqualTo("FooFoo");
    }

    @Test
    public void test037() {
        assertThat(fooBarQixToTest.fooBarQix(37)).isEqualTo("FooQix");
    }

    @Test
    public void test038() {
        assertThat(fooBarQixToTest.fooBarQix(38)).isEqualTo("Foo");
    }

    @Test
    public void test039() {
        assertThat(fooBarQixToTest.fooBarQix(39)).isEqualTo("FooFoo");
    }

    @Test
    public void test040() {
        assertThat(fooBarQixToTest.fooBarQix(40)).isEqualTo("Bar");
    }

    @Test
    public void test041() {
        assertThat(fooBarQixToTest.fooBarQix(41)).isEqualTo("41");
    }

    @Test
    public void test042() {
        assertThat(fooBarQixToTest.fooBarQix(42)).isEqualTo("FooQix");
    }

    @Test
    public void test043() {
        assertThat(fooBarQixToTest.fooBarQix(43)).isEqualTo("Foo");
    }

    @Test
    public void test044() {
        assertThat(fooBarQixToTest.fooBarQix(44)).isEqualTo("44");
    }

    @Test
    public void test045() {
        assertThat(fooBarQixToTest.fooBarQix(45)).isEqualTo("FooBarBar");
    }

    @Test
    public void test046() {
        assertThat(fooBarQixToTest.fooBarQix(46)).isEqualTo("46");
    }

    @Test
    public void test047() {
        assertThat(fooBarQixToTest.fooBarQix(47)).isEqualTo("Qix");
    }

    @Test
    public void test048() {
        assertThat(fooBarQixToTest.fooBarQix(48)).isEqualTo("Foo");
    }

    @Test
    public void test049() {
        assertThat(fooBarQixToTest.fooBarQix(49)).isEqualTo("Qix");
    }

    @Test
    public void test050() {
        assertThat(fooBarQixToTest.fooBarQix(50)).isEqualTo("BarBar");
    }

    @Test
    public void test051() {
        assertThat(fooBarQixToTest.fooBarQix(51)).isEqualTo("FooBar");
    }

    @Test
    public void test052() {
        assertThat(fooBarQixToTest.fooBarQix(52)).isEqualTo("Bar");
    }

    @Test
    public void test053() {
        assertThat(fooBarQixToTest.fooBarQix(53)).isEqualTo("BarFoo");
    }

    @Test
    public void test054() {
        assertThat(fooBarQixToTest.fooBarQix(54)).isEqualTo("FooBar");
    }

    @Test
    public void test055() {
        assertThat(fooBarQixToTest.fooBarQix(55)).isEqualTo("BarBarBar");
    }

    @Test
    public void test056() {
        assertThat(fooBarQixToTest.fooBarQix(56)).isEqualTo("QixBar");
    }

    @Test
    public void test057() {
        assertThat(fooBarQixToTest.fooBarQix(57)).isEqualTo("FooBarQix");
    }

    @Test
    public void test058() {
        assertThat(fooBarQixToTest.fooBarQix(58)).isEqualTo("Bar");
    }

    @Test
    public void test059() {
        assertThat(fooBarQixToTest.fooBarQix(59)).isEqualTo("Bar");
    }

    @Test
    public void test060() {
        assertThat(fooBarQixToTest.fooBarQix(60)).isEqualTo("FooBar");
    }

    @Test
    public void test061() {
        assertThat(fooBarQixToTest.fooBarQix(61)).isEqualTo("61");
    }

    @Test
    public void test062() {
        assertThat(fooBarQixToTest.fooBarQix(62)).isEqualTo("62");
    }

    @Test
    public void test063() {
        assertThat(fooBarQixToTest.fooBarQix(63)).isEqualTo("FooQixFoo");
    }

    @Test
    public void test064() {
        assertThat(fooBarQixToTest.fooBarQix(64)).isEqualTo("64");
    }

    @Test
    public void test065() {
        assertThat(fooBarQixToTest.fooBarQix(65)).isEqualTo("BarBar");
    }

    @Test
    public void test066() {
        assertThat(fooBarQixToTest.fooBarQix(66)).isEqualTo("Foo");
    }

    @Test
    public void test067() {
        assertThat(fooBarQixToTest.fooBarQix(67)).isEqualTo("Qix");
    }

    @Test
    public void test068() {
        assertThat(fooBarQixToTest.fooBarQix(68)).isEqualTo("68");
    }

    @Test
    public void test069() {
        assertThat(fooBarQixToTest.fooBarQix(69)).isEqualTo("Foo");
    }

    @Test
    public void test070() {
        assertThat(fooBarQixToTest.fooBarQix(70)).isEqualTo("BarQixQix");
    }

    @Test
    public void test071() {
        assertThat(fooBarQixToTest.fooBarQix(71)).isEqualTo("Qix");
    }

    @Test
    public void test072() {
        assertThat(fooBarQixToTest.fooBarQix(72)).isEqualTo("FooQix");
    }

    @Test
    public void test073() {
        assertThat(fooBarQixToTest.fooBarQix(73)).isEqualTo("QixFoo");
    }

    @Test
    public void test074() {
        assertThat(fooBarQixToTest.fooBarQix(74)).isEqualTo("Qix");
    }

    @Test
    public void test075() {
        assertThat(fooBarQixToTest.fooBarQix(75)).isEqualTo("FooBarQixBar");
    }

    @Test
    public void test076() {
        assertThat(fooBarQixToTest.fooBarQix(76)).isEqualTo("Qix");
    }

    @Test
    public void test077() {
        assertThat(fooBarQixToTest.fooBarQix(77)).isEqualTo("QixQixQix");
    }

    @Test
    public void test078() {
        assertThat(fooBarQixToTest.fooBarQix(78)).isEqualTo("FooQix");
    }

    @Test
    public void test079() {
        assertThat(fooBarQixToTest.fooBarQix(79)).isEqualTo("Qix");
    }

    @Test
    public void test080() {
        assertThat(fooBarQixToTest.fooBarQix(80)).isEqualTo("Bar");
    }

    @Test
    public void test081() {
        assertThat(fooBarQixToTest.fooBarQix(81)).isEqualTo("Foo");
    }

    @Test
    public void test082() {
        assertThat(fooBarQixToTest.fooBarQix(82)).isEqualTo("82");
    }

    @Test
    public void test083() {
        assertThat(fooBarQixToTest.fooBarQix(83)).isEqualTo("Foo");
    }

    @Test
    public void test084() {
        assertThat(fooBarQixToTest.fooBarQix(84)).isEqualTo("FooQix");
    }

    @Test
    public void test085() {
        assertThat(fooBarQixToTest.fooBarQix(85)).isEqualTo("BarBar");
    }

    @Test
    public void test086() {
        assertThat(fooBarQixToTest.fooBarQix(86)).isEqualTo("86");
    }

    @Test
    public void test087() {
        assertThat(fooBarQixToTest.fooBarQix(87)).isEqualTo("FooQix");
    }

    @Test
    public void test088() {
        assertThat(fooBarQixToTest.fooBarQix(88)).isEqualTo("88");
    }

    @Test
    public void test089() {
        assertThat(fooBarQixToTest.fooBarQix(89)).isEqualTo("89");
    }

    @Test
    public void test090() {
        assertThat(fooBarQixToTest.fooBarQix(90)).isEqualTo("FooBar");
    }

    @Test
    public void test091() {
        assertThat(fooBarQixToTest.fooBarQix(91)).isEqualTo("Qix");
    }

    @Test
    public void test092() {
        assertThat(fooBarQixToTest.fooBarQix(92)).isEqualTo("92");
    }

    @Test
    public void test093() {
        assertThat(fooBarQixToTest.fooBarQix(93)).isEqualTo("FooFoo");
    }

    @Test
    public void test094() {
        assertThat(fooBarQixToTest.fooBarQix(94)).isEqualTo("94");
    }

    @Test
    public void test095() {
        assertThat(fooBarQixToTest.fooBarQix(95)).isEqualTo("BarBar");
    }

    @Test
    public void test096() {
        assertThat(fooBarQixToTest.fooBarQix(96)).isEqualTo("Foo");
    }

    @Test
    public void test097() {
        assertThat(fooBarQixToTest.fooBarQix(97)).isEqualTo("Qix");
    }

    @Test
    public void test098() {
        assertThat(fooBarQixToTest.fooBarQix(98)).isEqualTo("Qix");
    }

    @Test
    public void test099() {
        assertThat(fooBarQixToTest.fooBarQix(99)).isEqualTo("Foo");
    }

    @Test
    public void test100() {
        assertThat(fooBarQixToTest.fooBarQix(100)).isEqualTo("Bar");
    }

}
