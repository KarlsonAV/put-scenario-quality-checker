package com.put.gamma.visitor;

import com.put.gamma.Element.Element;
import com.put.gamma.scenario.Scenario;
import com.put.gamma.section.Section;

/**
 * This class implements Visitor interface and is suited for counting main steps in a scenario.
 */
public class VisitorMainSteps implements Visitor {
    private int mainStepsCount = 0;

    /**
     * Visits the scenario and starts counting main steps.
     * @param scenario - the Scenario object to be visited
     */
    @Override
    public void visit(Scenario scenario) {
        mainStepsCount = 0;
        scenario.accept(this, 1);  // Start visiting with depth 1
    }

    /**
     * Visits a section, increments the main steps count if the section is a main step.
     * @param section - the Section object to be visited
     */
    @Override
    public void visit(Section section) {
        if (section.getDepth() == 1) {
            mainStepsCount++;
        }
        section.accept(this, 0);  // Continue visiting with depth 0
    }

    /**
     * Gets the count of main steps.
     * @return the count of main steps
     */
    public int getMainStepsCount() {
        return mainStepsCount;
    }
}
