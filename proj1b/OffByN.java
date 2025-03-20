public class OffByN implements CharacterComparator{
    public int N;
    public OffByN(int n){
        N = n;
    }
    @Override
    public boolean equalChars(char x, char y){
        int diff = y - x;
        if (diff == N || diff == -N){
            return true;
        }
        return false;
    }
}
