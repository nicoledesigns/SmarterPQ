import java.util.Comparator;

//comaparator to compare the numerical values of integers
//returns i > 0 if x > y
//returns i < 0 if x < y
//returns i = 0 if x = y
public class NumericalValue implements Comparator<Integer> {
    public int compare(Integer x, Integer y) {
        return x.intValue() - y.intValue();
    }
}