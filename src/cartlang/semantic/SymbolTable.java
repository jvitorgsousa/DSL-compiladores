package cartlang.semantic;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    public enum Type { INT, FLOAT, STRING, UNKNOWN }

    public static class Symbol {
        public String name;
        public Type type;
        public Object value;

        public Symbol(String name, Type type) {
            this.name = name;
            this.type = type;
        }
    }

    private final Map<String, Symbol> symbols = new HashMap<>();

    public boolean declare(String name, Type type) {
        if (symbols.containsKey(name)) return false;
        symbols.put(name, new Symbol(name, type));
        return true;
    }

    public Symbol get(String name) {
        return symbols.get(name);
    }

    public boolean isDeclared(String name) {
        return symbols.containsKey(name);
    }
}