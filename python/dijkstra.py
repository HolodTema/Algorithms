import sys 

# def dijkstra(graph, start):
#     #In the beginning the weight of other vertexes is infinity.
#     #And the weight of the starting vertex is zero.

#     distances = { vertex: float('infinity') for vertex in graph }
#     distances[start] = 0

#     queue = [(0, start)]
#     while queue:
#         current_distance, current_vertex = heapq.heappop(queue)

#         #we need to handle only the vertex with the smallest distance
#         if current_distance>distances[current_vertex]:
#             continue

#         for neighbor, weight in graph[current_vertex].items():
#             distance = current_distance + weight

#         #we handle this path only in case when it is better then others
#         if distance < distances[neighbor]:
#             distances[neighbor] = distance
#             heapq.heappush(queue, (distance, neightbor))
    
#     return distances


# def main():
#     graph = {
#         'A': {'B': 1, 'C': 3},
#         'B': {'A': 1, 'C': 2},
#         'C': {'A': 3, 'B': 2}
#     }
#     start = 'A'

#     print(dijkstra(graph, start))


class Graph(object):
    def __init__(self, nodes, init_graph):
        self.nodes = nodes
        self.graph = self.construct_graph(nodes, init_graph)
    
    def construct_graph(self, nodes, init_graph):
        # this method controls the symmerty of the graph. 
        # so, if the way from A to B exists and has len = V,
        # then the way from B to A also exists with len = V too.
        graph = {}
        for node in nodes:
            graph[node] = {}
        
        graph.update(init_graph)
        
        for node, edges in graph.items():
            for adjacent_node, value in edges.items():
                if graph[adjacent_node].get(node, False) == False:
                    graph[adjacent_node][node] = value
                    
        return graph

    def get_nodes(self):
        return self.nodes
    
    def get_outgoing_edges(self, node):
        # it returns neighbor nodes of the node
        connections = []
        for out_node in self.nodes:
            if self.graph[node].get(out_node, False) != False:
                connections.append(out_node)
        return connections

    def value(self, node1, node2):
        # returns edge value between 2 nodes
        return self.graph[node1][node2]


def dijkstra_algorithm(graph: Graph, start_node: str):
    unvisited_nodes = list(graph.get_nodes())
    shortest_path = dict()
    previous_nodes = dict()

    # to set distances to unvisited nodes, we use sys.maxsize
    max_value = sys.maxsize
    for node in unvisited_nodes:
        shortest_path[node] = max_value
    # but the start node distance is 0
    shortest_path[start_node] = 0

    while len(unvisited_nodes)>0:
        current_min_node = None
        # we have to choose the node with the most min distance
        for node in unvisited_nodes:
            if current_min_node==None:
                current_min_node = node
            elif shortest_path[node]<shortest_path[current_min_node]:
                current_min_node = node
        # now we found the node to work with
        # and we need to get all the neighbors of this node:
        neighbors = graph.get_outgoing_edges(current_min_node)
        for neighbor in neighbors:
            tentative_value = shortest_path[current_min_node] + \
                + graph.value(current_min_node, neighbor)
            if tentative_value < shortest_path[neighbor]:
                shortest_path[neighbor] = tentative_value
                previous_nodes[neighbor] = current_min_node
        
        unvisited_nodes.remove(current_min_node)

        return previous_nodes, shortest_path

    
def print_result(previous_nodes, shortest_path, start_node, target_node):
    path = []
    node = target_node
    while node!=start_node:
        path.append(node)
        node = previous_nodes[node]
    path.append(start_node)

    print("Found the best route:")
    print("Distance =", shortest_path[target_node])
    print("Succession =", " ".join(reversed(path)))


def main():
    nodes = ["Reykjavik", "Oslo", "Moscow", "London", "Rome", "Berlin", "Belgrade", "Athens"]
    init_graph = {}
    for node in nodes:
        init_graph[node] = {}

    init_graph["Reykjavik"]["Oslo"] = 5
    init_graph["Reykjavik"]["London"] = 4
    init_graph["Oslo"]["Berlin"] = 1
    init_graph["Oslo"]["Moscow"] = 3
    init_graph["Moscow"]["Belgrade"] = 5
    init_graph["Moscow"]["Athens"] = 4
    init_graph["Athens"]["Belgrade"] = 1
    init_graph["Rome"]["Berlin"] = 2
    init_graph["Rome"]["Athens"] = 2

    graph = Graph(nodes, init_graph)
    previous_nodes, shortest_path = dijkstra_algorithm(graph=graph, start_node="Reykjavik")
    print_result(previous_nodes=previous_nodes, shortest_path=shortest_path, start_node="Reykjavik", target_node="Belgrade")
main()
