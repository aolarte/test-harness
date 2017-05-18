package euler.problem22
class Problem22 {
    static void main(String... args) {
        println Problem22.getResource('/euler/problem22/p022_names.txt').text
                .split(",").sort()
                .collect { it.toUpperCase().bytes.findAll { it != 34 }.collect { i -> i - 64 }.sum() }
                .indexed().collect { index, item -> item * (index + 1) }
                .sum()
    }
}
