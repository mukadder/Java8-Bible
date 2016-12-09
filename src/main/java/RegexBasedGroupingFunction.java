import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class RegexBasedGroupingFunction<T> implements Function<T, String> {
    final Map<String, String> groupNameToRegex = new HashMap<>();

    public RegexBasedGroupingFunction(Map<String, String> groupNameToRegex) {
      if (groupNameToRegex != null) {
        this.groupNameToRegex.putAll(groupNameToRegex);
      }
    }

    @Override
    public String apply(T t) {
      return groupNameToRegex.entrySet()
          .stream()
          .filter(entry -> t.toString().matches(entry.getValue()))
          .map(entry -> entry.getKey())
          .findFirst()
          .orElse("default");
    }
}