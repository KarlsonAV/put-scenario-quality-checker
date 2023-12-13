package Element;

import visitor.Visitor;

public interface Element {
    public void accept(Visitor visitor);
}
