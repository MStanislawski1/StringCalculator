import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CalcTest {
    @org.junit.Test
    public void add() {
        assertThat(Calc.add(""),is(0));
    }

    @Test
    public void singleNumber(){
        assertThat(Calc.add("2"),is(2));
        assertThat(Calc.add("54"),is(54));
    }

    @Test
    public void sumOf2NumbersWithSeparator(){
        assertThat(Calc.add("1,2"),is(3));
        assertThat(Calc.add("5,3"),is(8));
    }

    @Test
    public void sumOfMoreThan2Numbers(){
        assertThat(Calc.add("1,2,3"), is(6));
        //assertThat(Calc.add("1,2,3,3,4,4"), is(17));
    }

    @Test
    public void sumOfNumbersWithNewLine(){
        assertThat(Calc.add("1\n2"), is(3));
        assertThat(Calc.add("3,2\n1"), is(6));
        assertThat(Calc.add("1\n2,3"), is(6));
    }

    @Test
    public void changedDelimiter(){
        assertThat(Calc.add("//;\n1;2"), is(3));
    }

    @Rule
    public ExpectedException expectedException=ExpectedException.none();

    @Test
    public void negativeNumbers(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Negative number -7");
        Calc.add("-7");
    }

    @Test
    public void moreNegativeNumbers(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Negative number -4,-7,-14");
        Calc.add("1,-4,-7,-14");
    }

    @Test
    public void numsOver1000(){
        assertThat(Calc.add("2,1001"), is(3));
    }
}