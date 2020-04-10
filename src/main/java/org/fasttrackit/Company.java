package org.fasttrackit;

public class Company {
    private Hierarchy hierarchy;

    public Company() {
        this.hierarchy = new Hierarchy();
    }

    public Company(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Employee getTopLevelManager() {
        return hierarchy.getManager();
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    @Override
    public String toString() {
        return "Company{" + "hierarchy=" + hierarchy + '}';
    }
}
