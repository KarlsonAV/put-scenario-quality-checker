package visitor;

import Element.Element;
import scenario.Scenario;
import section.Section;

import java.util.ArrayList;
import java.util.List;

public interface Visitor {
    List<Element> elements=new ArrayList<Element>();
    void visit(Scenario scenario);
    void visit(Section section);
}
