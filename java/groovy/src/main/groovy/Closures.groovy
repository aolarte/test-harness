class Closures {

    static void main(String... args) {
        Map<String,Object> map = ['1' : true, '2': '9.99', '3':8.77d]
        println parseRuleAsDouble(map,'2')
        println parseRuleAsDouble(map,'3')
    }

    static double parseRuleAsDouble(Map<String,Object> rules, String ruleName) {
        parseRule(rules, ruleName) {
            if (it instanceof Number) {
                return ((Number) it).doubleValue()
            }
            return Double.parseDouble(it.toString())
        }
    }


    static boolean parseRuleAsBoolean(Map<String,Object> rules, String ruleName) {
        parseRule(rules, ruleName) {
            if (it instanceof Boolean) {
                return ((Boolean) it).booleanValue()
            }
            return Boolean.parseBoolean(it.toString())
        }
    }

    static Object parseRule(Map<String,Object> rules, String ruleName, Closure closure) {
        try {
            Object value = rules[ruleName]
            return closure.call(value)
        } catch (NullPointerException npe) {
            throw new RuntimeException("Rule not found: ${ruleName}", npe)
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Rule ${ruleName} cannot be parsed to integer: ${rules[ruleName]}", nfe)
        }

    }
}
