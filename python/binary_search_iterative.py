


def binary_search(l: list, x):
    low = 0
    high = len(l) - 1
    m = 0
    while low<=high:
        m = (low+high)//2
        if l[m]==x:
            return m
        elif l[m]>x:
            high = m-1
        else:
            low = m+1
    return -1

def main():
    l = [1, 5, 8, 14, 26, 100, 315, 643]
    print(binary_search(l, 100))

main()
