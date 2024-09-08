

def binary_search(l: list, low: int, high: int, x: int):
    if high >= low:
        m = (high+low) // 2
        if l[m] == x:
            return m
        elif l[m] > x:
            binary_search(l, low, m-1, x)
        else:
            binary_search(l, m+1, high, x)
    else:
        return -1


def main():
    l = [1, 3, 5, 11, 104, 200, 1000]
    print(binary_search(l, 0, len(l)-1, 11))

main()
