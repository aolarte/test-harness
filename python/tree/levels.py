
def combine(map1, map2):
    ret = {}
    for key, value in map1.items():
        v1 = ret.get(key,0)
        v2 = v1 + value
        ret[key]=v2
    for key, value in map2.items():
        v1 = ret.get(key,0)
        v2 = v1 + value
        ret[key]=v2
    return ret


class Node:
    def __init__(self, value, children):
        self.value = value
        self.children = children

    def calculate_levels(self):
        return self.calculate_levels_l(0)

    def calculate_levels_l(self, level):
        my_map={level: self.value}
        for x in self.children:
            child_map = x.calculate_levels_l(level+1)
            my_map = combine(my_map, child_map)
        return my_map

child11 = Node(6,[])
child12 = Node(1,[])
child13 = Node(4,[])
child14 = Node(5,[])

child21 = Node(1,[child11])
child22 = Node(5,[child12, child13])
child23 = Node(10,[child14])


root = Node(10,[child21, child22, child23])
print(root.calculate_levels())


