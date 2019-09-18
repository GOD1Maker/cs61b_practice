public class OffByN implements CharacterComparator{
   private int diff;
    public OffByN(int N){
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int actualDiff = x - y;
        if(Math.abs(actualDiff) != diff){
            return false;
        }else {
            return true;
        }

    }
}
