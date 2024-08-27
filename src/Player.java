import java.util.ArrayList;
import java.util.Collection;

public class Player extends ArrayList<Card> {
  private String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public Player(Collection<? extends Card> c, String name) {
        super(c);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
