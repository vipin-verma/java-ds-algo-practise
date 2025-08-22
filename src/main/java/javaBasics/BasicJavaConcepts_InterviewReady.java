package javaBasics;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/**
 * Interview-ready single-file Java demo (Java 17+ compatible)
 * - Shows core language features with clean structure & best practices
 * - Each section is small, focused, and runnable
 */
public class BasicJavaConcepts_InterviewReady {

    // ===== 0) Constants & Static State =====
    private static final String LANGUAGE = "Java"; // immutable constant
    private static int totalStudents = 0;           // shared mutable state (for demo)

    public static void main(String[] args) {
        System.out.println("\n=== Basic Java Concepts (Interview-Ready) ===\n");
        section("1. Data Types & Variables", BasicJavaConcepts_InterviewReady::demoPrimitivesAndWrappers);
        section("2. Operators", BasicJavaConcepts_InterviewReady::demoOperators);
        section("3. Type Casting", BasicJavaConcepts_InterviewReady::demoTypeCasting);
        section("4. Strings & StringBuilder", BasicJavaConcepts_InterviewReady::demoStrings);
        section("5. Arrays", BasicJavaConcepts_InterviewReady::demoArrays);
        section("6. Collections & Streams", BasicJavaConcepts_InterviewReady::demoCollectionsAndStreams);
        section("7. OOP: classes, records, enums, equals/hashCode", BasicJavaConcepts_InterviewReady::demoOOP);
        section("8. Optional & Null-safety", BasicJavaConcepts_InterviewReady::demoOptional);
        section("9. Exceptions & try-with-resources", BasicJavaConcepts_InterviewReady::demoExceptions);
        section("10. Date-Time API", BasicJavaConcepts_InterviewReady::demoDateTime);
        section("11. BigDecimal (money-safe)", BasicJavaConcepts_InterviewReady::demoBigDecimal);
        section("12. Concurrency (Executors)", BasicJavaConcepts_InterviewReady::demoConcurrency);
        section("13. Static vs Instance", BasicJavaConcepts_InterviewReady::demoStaticVsInstance);
    }

    // Utility to print section titles & run demos
    private static void section(String title, Runnable demo) {
        System.out.println("\n--- " + title + " ---");
        demo.run();
    }

    // ===== 1) Data Types & Variables =====
    private static void demoPrimitivesAndWrappers() {
        byte b = 127; // -128..127
        short s = 32_767;
        int i = 2_147_483_647;
        long l = 9_223_372_036_854_775_807L;
        float f = 3.14f;
        double d = 3.141592653589793;
        char c = 'A';
        boolean flag = true;

        // Wrapper types & autoboxing
        Integer wi = i; // autobox
        int unboxed = wi; // unbox

        System.out.printf("byte=%d short=%d int=%d long=%d\n", b, s, i, l);
        System.out.printf("float=%.2f double=%f char=%c boolean=%b\n", f, d, c, flag);
        System.out.println("Wrapper Integer (autoboxed)=" + wi + ", unboxed=" + unboxed);

        // Safe arithmetic: addExact throws on overflow (good to mention in interviews)
        try {
            Math.addExact(i, 1); // will throw ArithmeticException (overflow)
        } catch (ArithmeticException ex) {
            System.out.println("addExact overflow caught: " + ex.getMessage());
        }
    }

    // ===== 2) Operators =====
    private static void demoOperators() {
        int a = 10, b = 3;
        System.out.printf("a+b=%d, a-b=%d, a*b=%d, a/b=%d, a%%b=%d\n", a+b, a-b, a*b, a/b, a%b);

        int x = 5;
        System.out.println("x=" + x + ", x++=" + (x++) + ", after=" + x + ", ++x=" + (++x));

        System.out.println("Comparisons: a==b=" + (a==b) + ", a>b=" + (a>b));
        boolean p = true, q = false;
        System.out.println("Logical: p&&q=" + (p&&q) + ", p||q=" + (p||q) + ", !p=" + (!p));

        int c = 15; c += 5; c -= 3; c *= 2; // compound assignment
        System.out.println("Compound result c=" + c);
    }

    // ===== 3) Type Casting =====
    private static void demoTypeCasting() {
        int i = 100; long l = i; float f = l; double d = f; // widening
        System.out.printf("widen: int->long->float->double = %d -> %d -> %f -> %f\n", i, l, f, d);

        double d2 = 100.99; long l2 = (long) d2; int i2 = (int) l2; byte b2 = (byte) i2; // narrowing
        System.out.printf("narrow: double->long->int->byte = %f -> %d -> %d -> %d\n", d2, l2, i2, b2);

        char ch = 'A'; int code = ch; System.out.println("'A' codepoint=" + code);
    }

    // ===== 4) Strings =====
    private static void demoStrings() {
        String s1 = "Hello"; String s2 = "World";
        String joined = s1 + " " + s2; // concatenation
        System.out.println("joined=" + joined + ", len=" + joined.length());
        System.out.println("upper=" + joined.toUpperCase() + ", contains 'World'=" + joined.contains("World"));

        // equals vs ==
        String a = "Java"; String b = new String("Java");
        System.out.println("a==b? " + (a==b)); // false (different references)
        System.out.println("a.equals(b)? " + a.equals(b)); // true (same content)

        // StringBuilder for many concatenations
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) sb.append("#").append(i);
        System.out.println("StringBuilder result=" + sb);
    }

    // ===== 5) Arrays =====
    private static void demoArrays() {
        int[] nums = {1,2,3,4,5};
        System.out.println("nums=" + Arrays.toString(nums));
        int sum = Arrays.stream(nums).sum();
        System.out.println("sum=" + sum);

        String[] fruits = {"Apple", "Banana", "Orange"};
        for (String f : fruits) System.out.print(f + " ");
        System.out.println();

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
    }

    // ===== 6) Collections & Streams =====
    private static void demoCollectionsAndStreams() {
        List<String> names = new ArrayList<>(List.of("alice", "bob", "charlie", "bob"));
        Set<String> unique = new HashSet<>(names); // removes duplicates
        Map<String, Integer> nameLengths = names.stream()
                .collect(Collectors.toMap(n -> n, String::length, (oldV, newV) -> oldV));

        System.out.println("unique=" + unique);
        System.out.println("nameLengths=" + nameLengths);

        // Stream pipeline: filter-map-sort-collect
        List<String> processed = names.stream()
                .filter(n -> n.length() >= 4)
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        System.out.println("processed=" + processed);

        // Grouping & counting
        Map<Integer, Long> byLen = names.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("groupedCountsByLength=" + byLen);
    }

    // ===== 7) OOP: class + record + enum =====
    private static void demoOOP() {
        // Class with encapsulation & value semantics
        Person p1 = new Person("Rahul", 25);
        Person p2 = new Person("Rahul", 25);
        System.out.println("p1.equals(p2)=" + p1.equals(p2) + ", hashEqual=" + (p1.hashCode()==p2.hashCode()));

        // Record: concise, immutable data-carrier
        Point pt = new Point(3, 4);
        System.out.println("record pt=" + pt + ", length=" + Math.hypot(pt.x(), pt.y()));

        // Enum + switch expression
        Level lvl = Level.HIGH;
        String action = switch (lvl) {
            case LOW  -> "log";
            case MED  -> "alert";
            case HIGH -> "page-oncall";
        };
        System.out.println("level=" + lvl + ", action=" + action);
    }

    // ===== 8) Optional =====
    private static void demoOptional() {
        Map<String, String> env = Map.of("REGION", "ap-south-1");
        String region = Optional.ofNullable(env.get("REGION"))
                .filter(r -> r.startsWith("ap-"))
                .orElse("unknown");
        System.out.println("region=" + region);
    }

    // ===== 9) Exceptions & try-with-resources =====
    private static void demoExceptions() {
        // try-with-resources auto-closes
        String data = "one\ntwo\nthree";
        try (BufferedReader br = new BufferedReader(new StringReader(data))) {
            System.out.println("firstLine=" + br.readLine());
        } catch (IOException e) {
            // in real apps prefer logging frameworks; keep messages actionable
            System.err.println("I/O error: " + e.getMessage());
        }

        // Custom checked exception example
        try {
            validateAge(15);
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }

    // ===== 10) Date-Time API =====
    private static void demoDateTime() {
        LocalDate dob = LocalDate.of(2000, 1, 15);
        Period age = Period.between(dob, LocalDate.now());
        System.out.printf("DOB=%s, age=%d years %d months %d days\n", dob, age.getYears(), age.getMonths(), age.getDays());

        ZonedDateTime nowIst = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formatted = nowIst.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z"));
        System.out.println("Now(IST)=" + formatted);
    }

    // ===== 11) BigDecimal for financial correctness =====
    private static void demoBigDecimal() {
        BigDecimal price = new BigDecimal("999.99");
        BigDecimal gst = new BigDecimal("0.18");
        BigDecimal total = price.add(price.multiply(gst)).setScale(2, RoundingMode.HALF_UP);
        System.out.println("totalWithGST=" + total);
    }

    // ===== 12) Concurrency (Executors) =====
    private static void demoConcurrency() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try {
            List<Callable<Integer>> tasks = List.of(
                    () -> IntStream.rangeClosed(1, 1_000).sum(),
                    () -> IntStream.rangeClosed(1, 1_000).map(x -> x*x).sum()
            );
            List<Future<Integer>> results = pool.invokeAll(tasks);
            for (Future<Integer> f : results) System.out.println("futureResult=" + f.get());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee) {
            System.err.println("Task failed: " + ee.getCause());
        } finally {
            pool.shutdown();
        }
    }

    // ===== 13) Static vs Instance =====
    private static void demoStaticVsInstance() {
        System.out.println("LANGUAGE (static const)=" + LANGUAGE + ", totalStudents=" + totalStudents);

        Student s1 = Student.of("Alice", 20);
        Student s2 = Student.of("Bob", 22);
        totalStudents = 2; // shared for demo only; prefer deriving counts from collections

        s1.printInfo();
        s2.printInfo();
        System.out.println("totalStudents(shared)=" + totalStudents);
    }

    // ===== Helpers & Demo Types =====

    private static void validateAge(int age) throws ValidationException {
        if (age < 18) throw new ValidationException("Age must be >= 18");
    }

    // Classic class with encapsulation and value semantics
    static class Person {
        private final String name;
        private final int age;
        Person(String name, int age) { this.name = Objects.requireNonNull(name); this.age = age; }
        public String name() { return name; }
        public int age() { return age; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person p)) return false; // pattern matching (Java 16+)
            return age == p.age && name.equals(p.name);
        }
        @Override public int hashCode() { return Objects.hash(name, age); }
        @Override public String toString() { return "Person{" + name + "," + age + '}'; }
    }

    // Record: immutable data class (auto equals/hashCode/toString)
    record Point(int x, int y) {}

    // Enum with meaningful states
    enum Level { LOW, MED, HIGH }

    // Custom checked exception
    static class ValidationException extends Exception { ValidationException(String m) { super(m); } }

    // Example of a small domain class + static factory
    static class Student {
        private final String name; private final int age;
        private Student(String name, int age) { this.name = name; this.age = age; }
        public static Student of(String name, int age) { return new Student(name, age); }
        void printInfo() {
            System.out.printf("Student{name='%s', age=%d, lang=%s}%n", name, age, LANGUAGE);
        }
    }
}
