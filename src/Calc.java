import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Calc {

    private String separator;
    private String num;

    private Calc(String separator, String num){
        this.separator=separator;
        this.num=num;
    }

    public static void main(String[] args){
        System.out.println("Hello World");

    }


    private IntStream getNumber(){
        if(num.isEmpty()){
            return  IntStream.empty();
        }
        else{
            return Arrays.stream(num.split(separator)).mapToInt(Integer::parseInt).map(n->n%1000);
        }
    }

    public void shouldBeNoNegative(){
        String negativeNums = getNumber().filter(n->n<0).mapToObj(Integer::toString).collect(Collectors.joining(","));
        if(!negativeNums.isEmpty()){
            throw new IllegalArgumentException("Exception " + negativeNums +" is negative numbers");
        }
    }

    public int add(){
        shouldBeNoNegative();
        return getNumber().sum();
    }

    public static int add(String variable){
        return parseInput(variable).add();
    }

    private static Calc parseInput(String variable){
        if(variable.startsWith("//")){
            String[] tab=variable.split("\n",2);
            return new Calc(tab[0].substring(2),tab[1]);
        }
        else {
            return new Calc(",|\n",variable);
        }
    }
}