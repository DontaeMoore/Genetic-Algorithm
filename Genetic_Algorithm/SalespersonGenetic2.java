package salespersongenetic2;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;











public class SalespersonGenetic2 {

    
    int[][] prices = new int[][] {
        { 0, 14, 4, 10, 20},
        { 14, 0, 7, 8, 7},
        { 4, 5, 0, 7, 16},
        { 11, 7, 9, 0, 2},
        { 18, 7, 17, 4, 0}
        
    }; //prices we will use to calculate fitness
    
    int[] path = new int[]
    {1,2,3,4,5};   //path unshuffled
    
    
    
   
    
    public int determineChromesome(float r, float[] normC){
        
        //manually do first once 
        if(r < normC[0]) {
          
            return 1;
        }
        
        for(int i = 0; i < normC.length-1; i++){
            if(r > normC[i] && r < normC[i+1]){
                return i+2;
            }
        }
        //else its the max number
        
        return normC.length;
        
        
        
       
    }
    
    
   
    
    
     public static int[] Mutate(int[] c){
       Random r = new Random();
       int randomInt = r.nextInt(1000) + 1;
       
       if(randomInt == 5){
           //mutate
           int randomIndex1 = r.nextInt(5);
           int randomIndex2 = r.nextInt(5);
           while(randomIndex2 == randomIndex1){
               randomIndex2 = r.nextInt(5);
           }
           //swap two elements
           int temp = c[randomIndex1];
           c[randomIndex1] = c[randomIndex2];
           c[randomIndex2] = temp;
           
           
       }
       return c;
       
        
    }
    
    public int[] getPath(){
        return path;
    }
    
    public int[] Crossover(int[] parent1, int[] parent2){
        int[] newGeneration = new int[5];
        boolean inBrackets = false;
        //my crossover picks a random part of the parent to start at,
        //and two elements starting at that index are then chosen
        //ex. 12[34]5
        //then my crossover looks at another chromesome, ex 34512 
        //and fills the other chromesome first with whats in the brackets, then 
        // from left to right the order the other numbers appear to create a chromesome AS LONG AS
        //THE NUMBER IS NOT CONTAINED WITHIN THE BRACKETS
        //ex. 12[34]5 to 25143 ---> 34251
        //just start with whats in the bracket and then fill out the rest as long as
        //its not in the bracket
        
        //random number from 0-4 will be our starting index
        //set endingindex to be greater then or equal to starting index to save me some hassel
        Random rn = new Random();
        int startIndex =0;
        int endIndex = 0;
        
        //calculate indexes that will hold in brackets some cities
        startIndex = rn.nextInt(5);
        if(startIndex == 4)
            endIndex = 4;
        else {
            endIndex = rn.nextInt(4-startIndex + 1) + startIndex;
        }
     
        //store them in brackets array
       int[] brackets = new int[endIndex - startIndex + 1];
       for(int i = 0; i < endIndex - startIndex + 1; i++){
           brackets[i] = parent1[startIndex + i];
          
       }
   
        
        
        //new generation starts off with whats in brackets array
        for(int i = 0; i < brackets.length; i++){
            newGeneration[i] = brackets[i];
        }
      
        
        int position = 0;
        
        for(int i = brackets.length; i < 5; i++){ //loop runs from brackets.length to 5
            for(int k = position;k < 5; k++){ //loop runs from every parent2 element
              
            for(int r = 0; r <brackets.length; r++){//this loop would check every parent element with whats in the brackets
                if(parent2[k] == brackets[r]) {
                    inBrackets = true;
                
                position++;
                }
            }
            if(inBrackets == false){ //add the item
            
                newGeneration[i] = parent2[k];
                position++;
                break; //break out of k loop
            }
            
            else {
               
                //do nothing, keep looking through parent2 for an element that is not in brackets
                inBrackets = false;
            }
        }
            
            
        }
        
        
        System.out.println();
        return newGeneration; //return new array which will be stored in a new chromesome
    }
   
    
    
    
    public static void main(String[] args) {
        SalespersonGenetic2 s = new SalespersonGenetic2();
        //generate the population
        float StartingDistance = 0;
        float endingDistance = 0;
       
        int population = 500;
        Chromesome[] c = new Chromesome[population];
        //generate new generations of chromesomes
         Chromesome[] generations = new Chromesome[population];
        
         int generationNumber = 500;
        for(int g = 0; g < generationNumber; g++){
            if(g == 0){
                //generate chromesomes for the first time 
                //give every chromesome c an array 
                System.out.println("INITIAL POPULATION  OF " + population + " CHROMESOMES");
        for(int i = 0;i < population; i++){
            c[i] = new Chromesome();
            c[i].routeRandomize();  //randomize the route
            c[i].calculateDistance(); //calculates distance 
            c[i].printRoute();  //print the route
           
        }
               
            }
            else {
                //use chromesomes in previous generation
                for(int i = 0; i < population; i++){
                    c[i] = generations[i];
                   
                }
            }
        
        
        
           
        
       
       
        
       
        float sum = 0;
        for(int i = 0; i < population; i++){ //calculate total sum
           sum = sum + c[i].getDistance();
        }
        System.out.println("Total sum for generation " + g + " is " + sum); //total sum
        if(g == 0)
            StartingDistance = sum;
        
        if(g == generationNumber - 1)
            endingDistance = sum;
        
        sum = 0;
        for(int i = 0; i < population; i++){ //calculate total normedDistance sum
           sum = sum + c[i].getNormedDistance();
        }
        float[] norms = new float[population];
        for(int i = 0; i < norms.length; i++){
            norms[i] = c[i].getNormedDistance() / sum;
           
        }
       
        
        //calculate cumulative norm
        float[] cumulative = new float[population];
        for(int i = 0; i < cumulative.length; i++){
            if(i == 0)
                cumulative[i] = norms[0];
            
            else {
                cumulative[i] = cumulative[i-1] + norms[i];
            }
        }
        
       
        
         //generate a number between 0-1 decimal
        Random rand = new Random();
        
       
        
        
         
    
        
         
         
         
         
         for(int i = 0; i < population; i++){ //generate new chromesomes
             
             //pick which two chromesomes will be parents
             
         float random = rand.nextFloat();
         
         int chromesomeNumber = s.determineChromesome(random,cumulative) - 1;
        
             
         
        float random2 = rand.nextFloat();
         
         int chromesomeNumber2 = s.determineChromesome(random2,cumulative) - 1;
             
        //create a new chromesome with the path equal to our crossing over
         generations[i] = new Chromesome();
         generations[i].setRoute(s.Crossover(c[chromesomeNumber].getRoute(), c[chromesomeNumber2].getRoute() ));
         //give it a chance to be mutated
         generations[i].setRoute(Mutate(generations[i].getRoute()));
        
        
         }
         
         //calculate new distance for this new generation
         int newsum = 0;
         for(int i = 0; i < population; i++){
             int distance = generations[i].calculateDistance();
                 newsum = newsum + distance;
             generations[i].printRoute();
         }
         
         System.out.println("New distance " + newsum);
            
         }
        
        //look through very new last generation for best path
        int maxDistance = 100;
        int[] maxTour = new int[5];
        for(int i = 0; i <population; i++){
          if(generations[i].getDistance() < maxDistance){
              maxDistance = generations[i].getDistance();
              maxTour = generations[i].getRoute();
          }
         }  
        
        //helpful statements
        System.out.println("Starting total distance in our population " + StartingDistance);
        System.out.println("Ending total distance in our population " + endingDistance);
        System.out.println("Using this genetic algorithm, our population used " + (StartingDistance - endingDistance) + 
                " less units of distance overall.");
        System.out.println("Fastest tour found in final generaton " + generationNumber);
        System.out.println("Distance: " + maxDistance + " With a tour of");
        
        for(int i = 0; i < 5; i++){
            System.out.print(maxTour[i] + " ");
        }
        System.out.println();
        
 
        
    }
       
    
}  

