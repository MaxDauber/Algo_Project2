

package algo_project2;

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

    public int constructPrunedGraph(int[][] image){
        int prunedWeight = 0;

        return prunedWeight;
    }

}
