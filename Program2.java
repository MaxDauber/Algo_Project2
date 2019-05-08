/*
 * Name: Meyer Dauber
 * EID: mjd3375
 */

package algo_project2;

import java.util.*;

public class Program2 {

    public int constructIntensityGraph(int[][] image){
        int intensityWeight = 0;
        //iterate through all pixels in graph
        for(int r = 0; r < image.length; r++){
            for(int c = 0; c < image[r].length; c++){
                //if on right edge of graph, add intensity of pixel and its lower counterpart
                if(c == image[r].length - 1 && !(r == image.length - 1)){
                    intensityWeight += Math.abs(image[r][c] - image[r+1][c]);
                }
                //if on bottom edge of graph, add intensity of pixel and its right counterpart
                else if (r == image.length - 1 && !(c == image[r].length - 1)){
                    intensityWeight += Math.abs(image[r][c] - image[r][c+1]);
                }
                //otherwise as long as not lower right pixel add both down and right intensities
                else if (!(r == image.length - 1) && !(c == image[r].length - 1)){
                    intensityWeight += Math.abs(image[r][c] - image[r+1][c]);
                    intensityWeight += Math.abs(image[r][c] - image[r][c+1]);
                }
            }
        }
        return intensityWeight;
    }

    public int constructPrunedGraph(int[][] image) {
        int prunedWeight = 0;
        int numVertices = image.length*image[0].length;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        ArrayList<Edge> minimum_spanning_tree = new ArrayList<Edge>();
        boolean[] visited = new boolean[numVertices];
        for(int i = 0; i < numVertices; i++)
            visited[i] = false;

        //starting with index 0 and going out from there
        int numVisited = 1;
        visited[0] = true;

        Edge right = new Edge(0, 1, getWeight(image, 0, 1));
        Edge down = new Edge(0, image[0].length, getWeight(image, 0, image[0].length));
        priorityQueue.offer(right);
        priorityQueue.offer(down);

        //while all nodes not visited
        while(numVisited < numVertices){

            //pop off smallest edge
            Edge e = priorityQueue.poll();

            //if destination not already connected
            if(!visited[Math.abs(e.destination)]){
                visited[e.destination] = true;
                prunedWeight += e.weight;
                minimum_spanning_tree.add(e);
                numVisited++;

                //add all adjacent nodes
                if(e.destination%image.length != 0){
                    Edge edge = new Edge(e.destination, e.destination - 1, getWeight(image, e.destination, e.destination - 1));
                    priorityQueue.offer(edge);
                }
                if(e.destination%image.length != image.length - 1){
                    Edge edge = new Edge(e.destination, e.destination + 1, getWeight(image, e.destination, e.destination + 1));
                    priorityQueue.offer(edge);
                }
                if(e.destination/image.length != 0){
                    Edge edge = new Edge(e.destination, e.destination - image[0].length, getWeight(image, e.destination, e.destination-image[0].length));
                    priorityQueue.offer(edge);
                }
                if(e.destination/image.length != image.length - 1){
                    Edge edge = new Edge(e.destination, e.destination + image[0].length, getWeight(image, e.destination, e.destination+image[0].length));
                    priorityQueue.offer(edge);
                }
            }
        }
        return prunedWeight;
    }


    public int getWeight(int[][] image, int a, int b){
        int x_a = Math.abs(a%image[0].length);
        int y_a = Math.abs(a/image[0].length);
        int x_b = Math.abs(b%image[0].length);
        int y_b = Math.abs(b/image[0].length);
        if(x_a >= 0&&x_b>=0&&y_b>=0&&y_a>=0&&x_a < image[0].length&&x_b<image[0].length&&y_b<image.length&&y_a<image.length)
            return Math.abs(image[y_a][x_a] - image[y_b][x_b]);
        return Integer.MAX_VALUE;
    }

    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o2) {
            if(this.weight - o2.weight < 0){
                return -1;
            }
            else if(((this.destination == o2.destination && this.source == o2.source)|| (this.source == o2.destination && this.destination == o2.source))&& this.weight == o2.weight){
                return 0;
            }
            return 1;
        }
    }

}
