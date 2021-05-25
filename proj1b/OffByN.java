public class OffByN implements CharacterComparator {
    int n;

    public OffByN() {
        n = 0;
    }

    public OffByN(int i) {
        n = i;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == n || y - x == n;
    }


}
