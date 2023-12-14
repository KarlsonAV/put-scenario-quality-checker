package visitor;

import Element.Element;
import scenario.Scenario;
import section.Section;

import java.util.ArrayList;
import java.util.List;

public class VisitorKeywords implements Visitor{
    public List<Element> elements=new ArrayList<Element>();
    public List<String> keywords = new ArrayList<String>();
    int result=0;
    @Override
    public void visit(Scenario scenario) {
        keywords=scenario.keywords;
        scenario.accept(this);
    }

    @Override
    public void visit(Section section) {
        section.accept(this);
        if(section.checkIfBeginsWithKeyword(keywords)==true){
            result++;
        }
    }
    public int getResult(){
        return result;
    }
}