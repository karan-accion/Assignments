import java.io.*;
import java.util.*;

public class practice {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add(1, "Mango");
        list.addAll(Arrays.asList("Date", "Elderberry"));
        list.set(2, "Orange");
        String first = list.get(0);
        int index = list.indexOf("Banana");
        list.remove("Apple");
        list.remove(0);
        list.contains("Mango");
        list.size();
        list.isEmpty();
        for (String item : list) {
            item.toLowerCase();
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.putIfAbsent(1, "Uno");
        map.putIfAbsent(3, "Three");
        map.get(2);
        map.getOrDefault(4, "None");
        map.containsKey(3);
        map.containsValue("Two");
        map.remove(2);
        map.size();
        map.keySet();
        map.values();
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            e.getKey();
            e.getValue();
        }

        Set<String> set = new HashSet<>();
        set.add("Red");
        set.add("Blue");
        set.add("Blue");
        set.addAll(Arrays.asList("Green", "Yellow"));
        set.remove("Green");
        set.contains("Red");
        set.size();
        set.isEmpty();
        for (String color : set) {
            color.length();
        }
        ArrayList<String> setList = new ArrayList<>(set);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int total = numbers.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
        int count = (int) numbers.stream().filter(n -> n > 2).count();
        numbers.stream().map(n -> n * 2).forEach(n -> {});
        numbers.stream().distinct().forEach(n -> {});
        numbers.stream().sorted().findFirst();
        numbers.stream().anyMatch(n -> n > 4);

        File file = new File("practice_data.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("one");
        writer.newLine();
        writer.write("two");
        writer.flush();
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            line = reader.readLine();
        }
        reader.close();
        file.exists();
        file.delete();

        try {
            int x = Integer.parseInt("100");
            int y = x / 2;
            int z = x / 0;
            String s = null;
            s.length();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            // finally block for cleanup
        }

        // ArrayList<String> al = new ArrayList<>();
        // al.add("A");
        // al.add("B");
        // al.clear();
        // al.contains("A");
        // HashMap<String, Integer> hm = new HashMap<>();
        // hm.put("key", 10);
        // hm.replace("key", 15);
        // hm.computeIfAbsent("key2", k -> 20);
        // hm.getOrDefault("missing", 0);
        // Set<Integer> hs = new HashSet<>();
        // hs.add(10);
        // hs.add(20);
        // hs.add(10);
        // hs.remove(20);
        // hs.contains(10);
        // List<String> names = Arrays.asList("Aman", "Riya");
        // names.stream().map(String::toUpperCase).forEach(System.out::println);
        // FileInputStream fis = new FileInputStream("temp.txt");
        // fis.close();
        // FileOutputStream fos = new FileOutputStream("temp2.txt");
        // fos.close();
        // int[] values = {1, 2, 3};
        // Arrays.stream(values).forEach(v -> {});
    }
}
