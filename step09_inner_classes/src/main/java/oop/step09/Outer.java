package oop.step09;

public class Outer {
    private String field = "Outer のフィールド値";

    // 1. Static nested class: no reference to Outer instance
    static class StaticNested {
        String describe() {
            return "StaticNested: 外部インスタンス不要";
        }
    }

    // 2. Inner class: holds implicit reference to Outer instance
    class Inner {
        String describe() {
            // Can access Outer's private field via implicit outer reference
            return "Inner: 外部フィールドにアクセス = " + field;
        }
    }

    // 3. Local class: defined inside a method body
    String createLocalClass(String localVar) {
        class Local {
            String describe() {
                // Captures effectively-final variables from enclosing scope
                return "Local: メソッド内変数をキャプチャ = " + localVar;
            }
        }
        return new Local().describe();
    }

    // 4. Anonymous class: one-shot implementation of Greeter interface
    Greeter createAnonymousGreeter() {
        return new Greeter() {
            @Override
            public String greet(String name) {
                return "こんにちは、" + name + "！";
            }
        };
    }
}
