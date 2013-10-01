import java.util.*;

public class TreeMapTest {    
    public static void main(String[] args) throws Exception {
        TreeMap<String, Double> map = 
                new TreeMap<String, Double>();
        map.put("ccc", 89.0);
        map.put("aaa", 80.0);
        map.put("zzz", 80.0);
        map.put("bbb", 89.0);
        System.out.println(map);
        System.out.println(map.lastEntry());
        System.out.println(map.lastKey());
        System.out.println(map.get("bbb"));
        
        final SortedSet<String> sorted = new TreeSet<String>();
        sorted.add("ccc");
        sorted.add("aaa");
        sorted.add("zzz");
        sorted.add("bbb");
        System.out.println(sorted);
        Collection c = new HashSet();
        try {
            c.add("asdf");
            c.add("aa");
            c.add("cly754");
            c.add(123 + 4);
            c.add(1.4);
            c.add(sorted);
            
            throw new Exception("asdf");
        }
        catch (Exception e) {
            System.out.println(c + "  " + e.getMessage());
        }
        
        TreeSet s = new TreeSet();
        s.add(1.3);
        s.add(2.0);
        s.add(4*1.0);
        System.out.println(s);
        
    }
}
