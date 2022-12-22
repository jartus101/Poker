import java.util.ArrayList;
public class App {
    public static void removeIndexZero(ArrayList<Integer> a){
        a.remove(0);
    }
    public static void main(String[] args){
        ArrayList<Integer> b = new ArrayList<>();
        b.add(5);
        b.add(7);
        b.add(8);
        removeIndexZero(b);
        for(int i = 0; i<b.size(); i++){
            System.out.println(b.get(i));
        }
    }
}