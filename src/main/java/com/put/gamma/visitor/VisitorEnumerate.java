package com.put.gamma.visitor;

import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Visitor interface and is designed for displaying enumerated scenarios.
 */
public class VisitorEnumerate implements Visitor {
    private List<Integer> sectionNumbers = new ArrayList<>();
    private StringBuilder result = new StringBuilder();

    /**
     * Visits the scenario and starts the enumeration process.
     * @param scenario - the Scenario object to be visited
     */
    @Override
    public void visit(Scenario scenario) {
        scenario.accept(this, 1);
    }

    /**
     * Visits a section, processes its enumeration, and recursively visits its subsections.
     * @param section - the Section object to be visited
     */
    @Override
    public void visit(Section section) {
        if (sectionNumbers.size() < section.getDepth()) {
            sectionNumbers.add(1);
        } else {
            while (sectionNumbers.size() > section.getDepth()) {
                sectionNumbers.remove(sectionNumbers.size() - 1);
            }
            sectionNumbers.set(section.getDepth() - 1, sectionNumbers.get(section.getDepth() - 1) + 1);
        }

        appendSectionNumber(sectionNumbers);
        result.append(section.content).append("\n");

        section.accept(this, 0);
    }

    /**
     * Gets the enumerated scenario as a string.
     * @return the enumerated scenario as a string
     */
    public String getEnumeratedScenario() {
        return result.toString();
    }

    /**
     * Appends section numbers to the result with a space after each number.
     * @param numbers - a list of section numbers
     */
    private void appendSectionNumber(List<Integer> numbers) {
        for (Integer number : numbers) {
            result.append(number).append(". ");
        }
    }
}
