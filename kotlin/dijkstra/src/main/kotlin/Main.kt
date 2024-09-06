package com.terabyte

import java.util.PriorityQueue

fun dijkstra(graph: Map<String, List<Pair<String, Int>>>, start: String): Map<String, Int> {
    val distances = mutableMapOf<String, Int>().withDefault { Int.MAX_VALUE }

    //list with visited nodes not to visit one node twice
    val visited = mutableListOf<String>()

    //priorityQueue orders all the nodes and guarantees that
    //that the nodes with min distance will be handled first.
    //
    //compareBy it.second - we need to compare all the nodes by their distance
    //(second in pair)
    //
    //and now it contains only one node - the start node
    val priorityQueue = PriorityQueue<Pair<String, Int>>(compareBy { it.second })
        .apply {
            add(start to 0)
        }

    distances[start] = 0

    while(priorityQueue.isNotEmpty()) {
        val (node, currentDist) = priorityQueue.poll()
        // iterate all the neighbors of the node with min dist value:
        if(graph[node]!=null) {
            for((adjacent, weight) in graph[node]!!) {
                if(adjacent in visited) continue
                //calculate dist to the neighbor if we go from the chosen node
                val totalDist = currentDist + weight
                //and if that distance is the most optimal - set it
                if(distances.containsKey(adjacent)) {
                    if(totalDist < distances[adjacent]!!) {
                        distances[adjacent] = totalDist
                        //and if we changed the data of another node, we need to add it to the queue
                        priorityQueue.add(adjacent to totalDist)
                    }
                }
                else {
                    distances[adjacent] = totalDist
                    //and if we changed the data of another node, we need to add it to the queue
                    priorityQueue.add(adjacent to totalDist)
                }
            }

        }
        visited.add(node)
    }
    return distances
}

fun main() {
    val graph = mapOf<String, List<Pair<String, Int>>>(
        "A" to listOf(
            Pair("B", 10), Pair("C", 15)
        ),
        "B" to listOf(
            Pair("D", 12)
        ),
        "C" to listOf(
            Pair("D", 15)
        ),
        "D" to listOf(
            Pair("E", 12), Pair("F", 15)
        ),
        "E" to emptyList(),
        "F" to emptyList()
    )
    val distances = dijkstra(graph, "A")
    println(distances)
}


