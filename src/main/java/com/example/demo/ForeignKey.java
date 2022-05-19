package com.example.demo;

public class ForeignKey {
    private String name;
    private Column parent;
    private Column child;

    public ForeignKey(String name, Column parent, Column child) {
        this.name = name;
        this.child = child;
        this.parent = parent;
    }

    public String getName() { return this.name; }
    public Column getParentColumn() { return this.parent; }
    public Column getChildColumn() { return this.child; }

    @Override
    public String toString() {
        return String.format(
                "%s (%s, %s) => (%s, %s)\n",
                name,
                child.getTable(), child.getColumn(),
                parent.getTable(), parent.getColumn()
        );
    }
}
