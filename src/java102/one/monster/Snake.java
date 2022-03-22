package java102.one.monster;

public class Snake extends Monster {
    public Snake() {
        super(4, "Snake", 3 + (int)(Math.random() * ((6- 3) + 1)), 12, 0);
    }
}
