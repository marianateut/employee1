package org.fasttrackit;
import java.util.ArrayList;
import java.util.List;

public class Hierarchy {

    private Employee manager;
    private List<Hierarchy> hierarchies;

    public Hierarchy() {
        hierarchies = new ArrayList<>();
    }

    public void addHierarchy(Hierarchy hierarchy) {
        hierarchies.add(hierarchy);
    }

    public List<Hierarchy> getHierarchies() {
        return hierarchies;
    }

    public void setManager(Employee owner) {
        this.manager = owner;
    }

    public Employee getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "Hierarchy{" + "manager=" + manager + ", hierarchies=" + hierarchies + '}';
    }
}


