package javaConcepts;

import java.util.*;

public final  class ImmutableSet<T> {

    private final Set<T> elements;

    private ImmutableSet(Set<T> elements) {
        this.elements = Collections.unmodifiableSet(elements);
    }

    public static <T> ImmutableSet<T> empty(){
        return  new ImmutableSet<>(Collections.emptySet());
    }

    public  ImmutableSet<T> add(T element){
        if(elements.contains(element)) return this;
        Set<T> copy = new HashSet<>(elements);
        copy.add(element);
        return new ImmutableSet<>(copy);
    }

    public boolean contains (T element ){

        return elements.contains(element);
    }


    public boolean isSubsetOf(ImmutableSet<T> other){
        if(this.elements.size() > other.elements.size()) return false ;
        return other.elements.containsAll(this.elements);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ImmutableSet<?> that)) return false;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elements);
    }

    @Override
    public String toString() {
        return "ImmutableSet{" +
                "elements=" + elements +
                '}';
    }


}

class TestImmutableSet {

    public static void main (String [] args )
    {
        ImmutableSet<Object> a = ImmutableSet.empty().add(1).add(2).add(3);
        ImmutableSet<Object> b = ImmutableSet.empty().add(1);

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        System.out.println("b subset hai kya a ka " + b.isSubsetOf(a));


    }

}
