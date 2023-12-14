package visitor;

import scenario.Scenario;
import section.Section;

public class VisitorCountSections implements Visitor{
    int sectionCount = 0;
    @Override
    public void visit(Scenario scenario){
        scenario.accept(this);
    }
    @Override
    public void visit(Section section){
        section.accept(this);
        sectionCount++;
    }
    public int getSectionCount(){
        return sectionCount;
    }
}
