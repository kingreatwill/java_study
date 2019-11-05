package com.demo.enumerations;

enum Shrubbery { GROUND, CRAWLING, HANGING;
}
public class EnumClass {
    public static void main(String[] args) {
        //Shrubbery.valueOf(""); // 是由编译器添加的 static 方法
        //Shrubbery.values(); //是由编译器添加的 static 方法
// 编译器将 枚举类 标记为 final 类，所以无法继承自 enum
        Enum e = Shrubbery.GROUND;

        for(Enum en : e.getClass().getEnumConstants())
            System.out.println(en);

        for(Shrubbery s : Shrubbery.values()) {
            System.out.println(
                    s + " ordinal: " + s.ordinal());
            System.out.println(
                    s.compareTo(Shrubbery.CRAWLING) + " ");
            System.out.println(
                    s.equals(Shrubbery.CRAWLING) + " ");
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("********************");
        }
// Produce an enum value from a String name:
        for(String s :
                "HANGING CRAWLING GROUND".split(" ")) {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);

            System.out.println(shrub);
        }
    }
}
