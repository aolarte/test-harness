package com.andresolarte.harness.spock.service

class TestService {
    int sum (int x, int y) {
        return x  +y
    }

    int subtract (int x, int y) {
        return x  -y
    }

    int sumAndSubtract (int x, int y, int z) {
        return subtract(sum(x,y), z)
    }
}
