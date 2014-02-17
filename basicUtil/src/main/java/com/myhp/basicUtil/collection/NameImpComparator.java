package com.myhp.basicUtil.collection;

import java.util.Comparator;

public class NameImpComparator {
    private final String firstName, lastName;

    public NameImpComparator(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new NullPointerException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public boolean equals(Object o) {
        System.out.println("equals()");
        if (!(o instanceof NameImpComparator))
            return false;
        NameImpComparator n = (NameImpComparator) o;
        return n.firstName.equals(firstName) && n.lastName.equals(lastName);
    }

    public int hashCode() {
        System.out.println("hashCode()");
        return 31 * firstName.hashCode() + lastName.hashCode();
    }

    public String toString() {
        System.out.println("toString()");
        return firstName + " " + lastName;
    }

    static final Comparator<NameImpComparator> SENIORITY_ORDER =
            new Comparator<NameImpComparator>() {
                public int compare(NameImpComparator e1, NameImpComparator e2) {
                    System.out.println("Comparator compare()");
                    int lastCmp = e1.lastName().compareTo(e2.lastName);
                    return (lastCmp != 0 ? lastCmp : e1.firstName().compareTo(e2.firstName()));
                }
            };
}