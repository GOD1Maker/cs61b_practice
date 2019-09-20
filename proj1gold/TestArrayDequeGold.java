import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();
    ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<Integer>();

    @Test
    public void randomTest(){
        for(int i = 0; i < 30; i++){
            int rand = StdRandom.uniform(3);
            switch (rand){
                case 0:
                    int first = StdRandom.uniform(10);
                    sad.addFirst(first);
                    ad.addFirst(first);
                    break;
                case 1:
                    int last = StdRandom.uniform(10);
                    sad.addLast(last);
                    ad.addLast(last);
                    break;
                case 2:
                    if(!ad.isEmpty()){
                        Integer rmvs = sad.removeFirst();
                        Integer rmva = ad.removeFirst();
                        assertEquals(rmva,rmvs);
                    }
                    break;
                case 3:
                    if(!ad.isEmpty()){
                        Integer rmvs = sad.removeLast();
                        Integer rmva = ad.removeLast();
                        assertEquals(rmva,rmvs);
                    }
                    break;
            }
            for(i = 0; i < ad.size(); i++){
                assertEquals( ad.get(i), sad.get(i));
            }

        }
    }
}
