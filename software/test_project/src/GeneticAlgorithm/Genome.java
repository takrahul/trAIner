package GeneticAlgorithm;

import java.util.Random;

public class Genome {
    int[][] directions;
    int step;
    int maxNrOfMoves;
//    int[][][] DNA;

    public Genome(int maxNrOfMoves) {
        this.step = 0;
        this.maxNrOfMoves = maxNrOfMoves;
        this.directions = new int[maxNrOfMoves][2];
        for(int i=0; i<maxNrOfMoves; i++){
            directions[i] = getRandomDirection;
        }
        this.randomize_directions(population_size);
        

    }

    private void randomizeDirections(int population_size) {
        for (int i = 0; i < population_size; i++) {
            this.directions[i] = get_random_direction();
        }

    }

    private int[] getRandomDirection() {
        int[] direction = new int[2];
        Random rand = new Random();
        int random_nr = rand.nextInt(8);
        switch (random_nr) {
            case 0:
                //runter
                direction[0] = 0;
                direction[1] = 1;
            case 1:
                // rauf
                direction[0] = 1;
                direction[1] = 1;
            case 2:
                // links
                direction[0] = 1;
                direction[1] = 0;
            case 3:
                // rechts
                direction[0] = 1;
                direction[1] = -1;
            case 4:
                //runter links
                direction[0] = 0;
                direction[1] = -1;
            case 5:
                // rauf links
                direction[0] = -1;
                direction[1] = -1;
            case 6:
                //runter rechts
                direction[0] = -1;
                direction[1] = 0;
            case 7:
                // rauf rechts
                direction[0] = -1;
                direction[1] = 1;

        }
        return direction;
    }

    public Genome clone() {
        Genome cloned_genome = new Genome(this.directions.length);
        for (int i = 0; i < this.directions.length; i++) {
            cloned_genome.directions[i] = this.directions[i];
        }
        return cloned_genome;
    }

    public void mutateGene(boolean dead, int death_by_step) {
        Random rand = new Random();


        for (int i = 0; i < this.directions.length; i++) {
            //int random_nr = rand.nextInt(1);
            if (dead && i > death_by_step - 420) {
                //random_nr = rand.nextDouble(0.420);
            }
            //TODO 
            //if (rand < 0.2) {
            //    this.directions[i] = this.get_random_direction();
            //}

        }
    }

    public void increase_number_of_moves() {
        //to do
    }
}
