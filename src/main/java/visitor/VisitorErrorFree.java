package visitor;

import scenario.Scenario;
import section.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorErrorFree implements Visitor{
    List<String> errorFreeSections = new ArrayList<>();
    @Override
    public void visit(Scenario scenario){
        scenario.accept(this);
    }
    @Override
    public void visit(Section section){
        section.accept(this);
    }
}
