import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test9 {
    public enum BasicEnum {
        BasicEnumType1("类型1", 1);

        private String named;
        private int value;
        BasicEnum(String named, int value) {
            this.named = named;
            this.value = value;
        }
        public String getNamed() {
            return this.named;
        }

        public int getValue(){
            return this.value;
        }

        public static Map<Integer, String> maps() {
            return Arrays.stream(BasicEnum.values()).collect(Collectors.toMap(BasicEnum::getValue, BasicEnum::getNamed));
        }

        public static BasicEnum vtoe(int value) {
            Optional<BasicEnum> op = Arrays.stream(BasicEnum.values()).filter(e -> e.getValue() == value).findFirst();
            return op.orElse(null);
        }
    }

    public enum Spiciness {
        NOT, MILD, MEDIUM, HOT, FLAMING
    }

    @Test
    public void test(){
        Spiciness howHot = Spiciness.MEDIUM;
        System.out.println(howHot);
        for (Spiciness s: Spiciness.values()) {
            System.out.println(s + ", ordinal " + s.ordinal());
        }
    }
}
