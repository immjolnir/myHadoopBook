package com.myhp.basicUtil.collection;

public class NameImpComparable implements Comparable<NameImpComparable> {
    private final String firstName, lastName;

    public NameImpComparable(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new NullPointerException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() { return firstName; }
    public String lastName()  { return lastName;  }

    public boolean equals(Object o) {
        System.out.println("equals()");
        if (!(o instanceof NameImpComparable))
            return false;
        NameImpComparable n = (NameImpComparable) o;
        return n.firstName.equals(firstName) && n.lastName.equals(lastName);
    }

    public int hashCode() {
        System.out.println("hashCode()");
        return 31*firstName.hashCode() + lastName.hashCode();
    }

    public String toString() {
        System.out.println("toString()");
        return firstName + " " + lastName;
    }

    public int compareTo(NameImpComparable n) {
        System.out.println("compareTo()");
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
    }
}